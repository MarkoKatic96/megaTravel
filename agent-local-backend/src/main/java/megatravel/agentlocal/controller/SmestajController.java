package megatravel.agentlocal.controller;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import megatravel.agentlocal.dto.SmestajDTO;
import megatravel.agentlocal.model.AgentModel;
import megatravel.agentlocal.model.SmestajModel;
import megatravel.agentlocal.service.AgentService;
import megatravel.agentlocal.service.SmestajService;
import megatravel.agentlocal.token.JwtTokenUtils;

@RestController
public class SmestajController {
	
	@Autowired
	SmestajService smestajService;
	
	@Autowired
	AgentService agentService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping(value = "api/smestaj/synchronize", method = RequestMethod.GET)
	public ResponseEntity<String> getCommunicationById(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("getCommunicationById()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AgentModel korisnik = agentService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		String host = "localhost:8080/api/smestaj/welcome";
		int port = 8080;
		SocketFactory tlsSocketFactory = SSLSocketFactory.getDefault();
		//s = tlsSocketFactory.createSocket(s, host, port, true);
		Socket socket = null;
		try {
			socket = tlsSocketFactory.createSocket(host, port);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DataInputStream in = null;
		try {
			in = new DataInputStream( 
			        new BufferedInputStream(socket.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
  
            String line = ""; 
  
            // reads message from client until "Over" is sent 
            while (!line.equals("Over")) 
            { 
                try
                { 
                    line = in.readUTF(); 
                    System.out.println(line); 
  
                } 
                catch(IOException e) 
                { 
                    System.out.println(e); 
                } 
            } 
            System.out.println("Closing connection"); 
  
            // close connection 
            try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
            try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		return new ResponseEntity<>(line, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/smestaj/welcome", method = RequestMethod.GET, produces = {	MediaType.TEXT_HTML_VALUE })
	public ResponseEntity<String> getWelcomeMessage(HttpServletRequest req) {
		System.out.println("getWelcomeMessage()");
		return new ResponseEntity<>("<html><body>Hello world</body></html>", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET, produces = { MediaType.TEXT_HTML_VALUE })
	public ResponseEntity<String> getErrorMessage(HttpServletRequest req) {
		System.out.println("getErrorMessage()");
		return new ResponseEntity<>("<html><body>Bad error, very bad :(</body></html>", HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/smestaj", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<SmestajDTO>> getAllSmestaji(HttpServletRequest req) {
		System.out.println("getAllSmestaji()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AgentModel korisnik = agentService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		
		List<SmestajModel> smestaji = smestajService.findAll();
		
		
		HttpHeaders headers = new HttpHeaders();
		long hoteliTotal = smestaji.size();
		headers.add("X-Total-Count", String.valueOf(hoteliTotal));

		List<SmestajDTO> retVal = new ArrayList<SmestajDTO>();

		for (SmestajModel s : smestaji) {
			retVal.add(new SmestajDTO(s));
		}

		return new ResponseEntity<>(retVal, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/smestaj/{id}", method = RequestMethod.GET)
	public ResponseEntity<SmestajDTO> getSmestaj(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("getSmestaj()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AgentModel korisnik = agentService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		SmestajModel smestaj = smestajService.findOne(id);
		if (smestaj==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		SmestajDTO smestajDTO = new SmestajDTO(smestaj);
		
		return new ResponseEntity<>(smestajDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/smestaj", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajDTO> create(@RequestBody SmestajDTO smestajDTO, HttpServletRequest req) {
		System.out.println("create()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AgentModel korisnik = agentService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		SmestajModel smestaj = new SmestajModel(smestajDTO.getId(), smestajDTO.getAdresa(), korisnik, smestajDTO.getCena(), smestajDTO.getOpis(), smestajDTO.getMaxOsoba());
		smestaj = smestajService.save(smestaj);
		
		korisnik.addSmestaj(smestaj);
		agentService.save(korisnik);

		return new ResponseEntity<>(new SmestajDTO(smestaj), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "api/smestaj/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		SmestajModel smestaj = smestajService.findOne(id);
		if (smestaj != null) {
			smestajService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} 
		else 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

package bezbednost.etapa2.appStarter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bezbednost.etapa2.dto.NadleznostDTO;
import bezbednost.etapa2.dto.RolaDTO;
import bezbednost.etapa2.model.Nadleznost;
import bezbednost.etapa2.model.Rola;

@RestController
public class NadleznostController {
	
	@Autowired
	private NadleznostService nadleznostService;
	
	@RequestMapping("api/getAllNadleznost")
	public ResponseEntity<List<Nadleznost>> getAllNadleznosti(){
		return new ResponseEntity<List<Nadleznost>>(nadleznostService.getAllNadleznosti(), HttpStatus.OK);
	}
	
	@RequestMapping("api/getNadleznostById/{id}")
	public ResponseEntity<Nadleznost> getNadleznostById(@PathVariable Long id){
		Nadleznost n = nadleznostService.getNadleznostById(id);
		return new ResponseEntity<Nadleznost>(n, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/deleteNadleznost/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteNadleznost(@PathVariable Long id){
		String poruka = nadleznostService.deleteNadleznost(id);
		return new ResponseEntity<String>(poruka, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/createNadleznost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Nadleznost> createNadleznost(@RequestBody NadleznostDTO dto){
		Long rolaId = dto.getRolaId();
		Long servisId = dto.getServisId();
		Nadleznost n = nadleznostService.createNadleznost(new Nadleznost(rolaId, servisId));
		return new ResponseEntity<Nadleznost>(n, HttpStatus.OK);
	}

}

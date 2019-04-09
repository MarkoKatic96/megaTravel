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

import bezbednost.etapa2.dto.RolaDTO;
import bezbednost.etapa2.model.Rola;

@RestController
public class RolaController {
	
	@Autowired
	RolaService rolaService;
	
	@RequestMapping("api/getAllRoles")
	public ResponseEntity<List<Rola>> getAllRoles(){
		return new ResponseEntity<List<Rola>>(rolaService.getAllRoles(), HttpStatus.OK);
	}
	
	@RequestMapping("api/getRoleById/{id}")
	public ResponseEntity<Rola> getRolaById(@PathVariable Long id){
		Rola r = rolaService.getRolaById(id);
		return new ResponseEntity<Rola>(r, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/deleteRole/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteRola(@PathVariable Long id){
		String poruka = rolaService.deleteRola(id);
		return new ResponseEntity<String>(poruka, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/createRole", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rola> createRola(@RequestBody RolaDTO dto){
		String naziv = dto.getNaziv();
		Rola r = rolaService.createRola(new Rola(naziv));
		return new ResponseEntity<Rola>(r, HttpStatus.OK);
	}
	
}

package bezbednost.etapa2.appStarter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bezbednost.etapa2.dto.ServisDTO;
import bezbednost.etapa2.model.Servis;

@RestController
public class ServisController {

	@Autowired
	ServisService servisService;
	
	@RequestMapping("api/getAllServices")
	public ResponseEntity<List<Servis>> getAllServices(){
		return new ResponseEntity<List<Servis>>(servisService.getAllServices(), HttpStatus.OK);
	}
	
	@RequestMapping("api/getServiceById/{id}")
	public ResponseEntity<Servis> getServisById(@PathVariable Long id){
		Servis s = servisService.getServisById(id);
		return new ResponseEntity<Servis>(s, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/deleteService/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteServis(@PathVariable Long id){
		String poruka = servisService.deleteServis(id);
		return new ResponseEntity<String>(poruka, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/createService", method = RequestMethod.POST)
	public ResponseEntity<Servis> createService(@RequestBody ServisDTO dto){
		String naziv = dto.getNaziv();
		Servis s = servisService.createServis(new Servis(naziv));
		return new ResponseEntity<Servis>(s, HttpStatus.OK);
	}
	
}

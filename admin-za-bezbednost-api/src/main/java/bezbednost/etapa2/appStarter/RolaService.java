package bezbednost.etapa2.appStarter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bezbednost.etapa2.model.Rola;

@Service
public class RolaService {

	@Autowired
	RolaRepository rolaRepository;
	
	public List<Rola> getAllRoles(){
		return rolaRepository.findAll();
	}
	
	public Rola getRolaById(Long id) {
		Rola r = rolaRepository.findOne(id);
		if(r!=null) {
			return r;
		}else {
			return null;
		}
	}
	
	public String deleteRola(Long id) {
		Rola r = rolaRepository.findOne(id);
		if(r!=null) {
			rolaRepository.delete(id);
			return "Odabrana rola uspesno obrisana.";
		}else {
			return "Odabrana rola nije pronadjena";
		}
	}
	
	public Rola createRola(Rola rola) {
		List<Rola> lista = rolaRepository.findAll();
		for (Rola rola2 : lista) {
			if(rola2.getNaziv().equals(rola.getNaziv())) {
				return null;
			}
		}
		
		return rolaRepository.save(rola);
	}
	
}

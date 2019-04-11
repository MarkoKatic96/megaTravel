package bezbednost.etapa2.appStarter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bezbednost.etapa2.model.Servis;

@Service
public class ServisService {
	
	@Autowired
	private ServisRepository servisRepository;
	
	public List<Servis> getAllServices(){
		return servisRepository.findAll();
	}
	
	public Servis getServisById(Long id) {
		Servis s = servisRepository.findOne(id);
		if(s!=null) {
			return s;
		}else {
			return null;
		}
	}
	
	public String deleteServis(Long id) {
		Servis s = servisRepository.findOne(id);
		if(s!=null) {
			servisRepository.delete(id);
			return "Odabran servis je uspesno obrisan.";
		}else {
			return "Odabran servis nije pronadjen.";
		}
	}
	
	public Servis createServis(Servis servis) {
		List<Servis> lista = servisRepository.findAll();
		for (Servis servis2 : lista) {
			if(servis2.getNaziv().equals(servis.getNaziv())) {
				return null;
			}
		}
		
		return servisRepository.save(servis);
	}

}

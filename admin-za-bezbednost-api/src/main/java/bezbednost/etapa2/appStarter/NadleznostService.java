package bezbednost.etapa2.appStarter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bezbednost.etapa2.model.Nadleznost;
import bezbednost.etapa2.model.Rola;

@Service
public class NadleznostService {
	
	@Autowired
	private NadleznostRepository nadleznostRepository;
	
	public List<Nadleznost> getAllNadleznosti(){
		return nadleznostRepository.findAll();
	}
	
	public Nadleznost getNadleznostById(Long id) {
		Nadleznost n = nadleznostRepository.findOne(id);
		if(n!=null) {
			return n;
		}else {
			return null;
		}
	}
	
	public String deleteNadleznost(Long id) {
		Nadleznost n = nadleznostRepository.findOne(id);
		if(n!=null) {
			nadleznostRepository.delete(id);
			return "Odabrana nadleznost uspesno obrisana.";
		}else {
			return "Odabrana nadleznost nije pronadjena";
		}
	}
	
	public Nadleznost createNadleznost(Nadleznost nadleznost) {
		List<Nadleznost> lista = nadleznostRepository.findAll();
		for (Nadleznost nadleznost2 : lista) {
			if(nadleznost2.getRolaId() == nadleznost.getRolaId() && nadleznost2.getServisId() == nadleznost.getServisId()) {
				return null;
			}
		}
		
		return nadleznostRepository.save(nadleznost);
	}

}

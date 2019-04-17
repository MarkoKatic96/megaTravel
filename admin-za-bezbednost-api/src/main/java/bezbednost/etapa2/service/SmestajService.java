package bezbednost.etapa2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bezbednost.etapa2.model.Korisnik;
import bezbednost.etapa2.model.SmestajModel;
import bezbednost.etapa2.repository.SmestajRepository;

@Service
public class SmestajService {
	
	@Autowired
	SmestajRepository smestajRepository;
	
	@Autowired
	KorisnikService korisnikService;
	
	public List<SmestajModel> findAll() {
		return smestajRepository.findAll();
	}
	
	public List<SmestajModel> findAllByOwner(String username){
		Korisnik k = korisnikService.findByEmail(username);
		List<SmestajModel> lista = findAll();
		List<SmestajModel> returnList = new ArrayList<SmestajModel>();
		for (SmestajModel smestajModel : lista) {
			if(smestajModel.getVlasnik().getId() == k.getId()) {
				returnList.add(smestajModel);
			}
		}
		return returnList;
	}

	public SmestajModel findOne(Long id) {
		return smestajRepository.findOne(id);
	}

	public SmestajModel save(SmestajModel smestaj) {
		return smestajRepository.save(smestaj);
	}

	public void remove(Long id) {
		smestajRepository.delete(id);
	}
}

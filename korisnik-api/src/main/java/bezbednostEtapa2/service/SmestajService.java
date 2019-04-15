package bezbednostEtapa2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bezbednostEtapa2.model.Smestaj;
import bezbednostEtapa2.repository.SmestajRepository;

@Service
public class SmestajService {
	
	@Autowired
	SmestajRepository smestajRepository;
	
	public List<Smestaj> findAll() {
		return smestajRepository.findAll();
	}

	public Smestaj findOne(Long id) {
		return smestajRepository.findOne(id);
	}

	public Smestaj save(Smestaj smestaj) {
		return smestajRepository.save(smestaj);
	}

	public void remove(Long id) {
		smestajRepository.delete(id);
	}
}

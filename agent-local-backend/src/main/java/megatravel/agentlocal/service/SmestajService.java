package megatravel.agentlocal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.agentlocal.model.SmestajModel;
import megatravel.agentlocal.repository.SmestajRepository;

@Service
public class SmestajService {
	
	@Autowired
	SmestajRepository smestajRepository;
	
	public List<SmestajModel> findAll() {
		return smestajRepository.findAll();
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

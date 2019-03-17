package megatravel.bezbednost.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import megatravel.bezbednost.model.CertificateCommunicationModel;
import megatravel.bezbednost.repository.CertificateCommunicationRepository;

@Service
public class CertificateCommunicationService {

	@Autowired
	CertificateCommunicationRepository certCommRepository;
	
	public Page<CertificateCommunicationModel> findAll(Pageable page) {
		return certCommRepository.findAll(page);
	}

	public CertificateCommunicationModel findOne(Long id) {
		return certCommRepository.findOne(id);
	}

	public CertificateCommunicationModel save(CertificateCommunicationModel certificateCommunicationModel) {
		return certCommRepository.save(certificateCommunicationModel);
	}

	public void remove(Long id) {
		certCommRepository.delete(id);
	}
	
	public boolean communicationApproved(BigInteger serijskiBroj1, BigInteger serijskiBroj2) {
		return !certCommRepository.communicationApproved(serijskiBroj1, serijskiBroj2).isEmpty();
	}
	
	public List<CertificateCommunicationModel> findBySerialNumbers(BigInteger serijskiBroj1, BigInteger serijskiBroj2) {
		return certCommRepository.communicationApproved(serijskiBroj1, serijskiBroj2);
	}
}

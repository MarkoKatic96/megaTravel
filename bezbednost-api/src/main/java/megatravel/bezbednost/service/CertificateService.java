package megatravel.bezbednost.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import megatravel.bezbednost.model.CertificateModel;
import megatravel.bezbednost.repository.CertificateRepository;

@Service
public class CertificateService {
	
	@Autowired
	CertificateRepository certificateRepository;
	
	public CertificateModel findBySerijskiBroj(BigInteger serijskiBroj) {
		return certificateRepository.findBySerijskiBroj(serijskiBroj);
	}
	
	public Page<CertificateModel> findAll(Pageable page) {
		return certificateRepository.findAll(page);
	}

	public CertificateModel findOne(Long id) {
		return certificateRepository.findOne(id);
	}

	public CertificateModel save(CertificateModel ceritifikat) {
		return certificateRepository.save(ceritifikat);
	}

	public void remove(Long id) {
		certificateRepository.delete(id);
	}
	
	public boolean existsBySerijskiBroj(BigInteger serijskiBroj) {
		return certificateRepository.existsBySerijskiBroj(serijskiBroj);
	}
	
	public Page<CertificateModel> findSubCertifikates(BigInteger idCertifikata, Pageable pageable){
		return certificateRepository.findSubCertifikates(idCertifikata, pageable);
	}
}

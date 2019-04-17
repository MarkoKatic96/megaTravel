package megatravel.bezbednost.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<CertificateModel> findAll() {
		return certificateRepository.findAll();
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
	
	public List<CertificateModel> findSubCertifikates(BigInteger idCertifikata){
		return certificateRepository.findSubCertifikates(idCertifikata);
	}
}

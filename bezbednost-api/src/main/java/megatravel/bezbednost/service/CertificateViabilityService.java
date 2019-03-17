package megatravel.bezbednost.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.bezbednost.model.CertificateViabilityModel;
import megatravel.bezbednost.model.StatusCertifikata;
import megatravel.bezbednost.repository.CertificateViabilityRepository;

@Service
public class CertificateViabilityService {
	
	@Autowired
	CertificateViabilityRepository certificateViabilityRepository;
	
	public List<CertificateViabilityModel> findAll(){
		List<CertificateViabilityModel> lista= certificateViabilityRepository.findAll();
		return lista;
	}
	
	public String getStatus(BigInteger serijskiBroj) {
		List<CertificateViabilityModel> lista= certificateViabilityRepository.findAll();
		StatusCertifikata status = null;
		for (CertificateViabilityModel cvm : lista) {
			if(cvm.getSerijskiBroj() == serijskiBroj) {
				status = cvm.getStatus();
			}
		}
		if(status == StatusCertifikata.ISTEKAO) {
			return "Sertifikat je istekao";
		}else if(status == StatusCertifikata.POVUCEN){
			return "Sertifikat je povucen";
		}else if(status == StatusCertifikata.VALIDAN) {
			return "Sertifikat je validan";
		}else {
			return "Doslo je do problema";
		}
	}
	
	public CertificateViabilityModel newStatus(CertificateViabilityModel certificateViabilityModel) {
		List<CertificateViabilityModel> lista= certificateViabilityRepository.findAll();
		for (CertificateViabilityModel cvm : lista) {
			if(cvm.getSerijskiBroj() == certificateViabilityModel.getSerijskiBroj()) {
				return null;
			}
		}
		return certificateViabilityRepository.save(certificateViabilityModel);
	}
	
	public CertificateViabilityModel editStatus(BigInteger sn, StatusCertifikata status) {
		List<CertificateViabilityModel> lista= certificateViabilityRepository.findAll();
		for (CertificateViabilityModel cvm : lista) {
			if(cvm.getSerijskiBroj() == sn) {
				cvm.setStatus(status);
				certificateViabilityRepository.save(cvm);
				return cvm;
			}
		}
		return new CertificateViabilityModel();
	}
}

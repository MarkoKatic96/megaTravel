package megatravel.bezbednost.service;

import java.math.BigInteger;
import java.util.ArrayList;
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
		return certificateViabilityRepository.findAll();
	}
	
	public List<CertificateViabilityModel> getValidCertificates(){
		List<CertificateViabilityModel> lista = certificateViabilityRepository.findAll();
		List<CertificateViabilityModel> listaValidnih = new ArrayList<CertificateViabilityModel>();
		for (CertificateViabilityModel cvm : lista) {
			if(cvm.getStatus()==StatusCertifikata.VALIDAN) {
				listaValidnih.add(cvm);
			}
		}
		return listaValidnih;
	}
	
	public StatusCertifikata getStatus(BigInteger serijskiBroj) {
		List<CertificateViabilityModel> lista= certificateViabilityRepository.findAll();
		StatusCertifikata status = null;
		for (CertificateViabilityModel cvm : lista) {
			if(cvm.getSerijskiBroj().equals(serijskiBroj)) {
				status = cvm.getStatus();
				break;
			}
		}
		/*if(status == StatusCertifikata.ISTEKAO) {
			return "Sertifikat je istekao";
		}else if(status == StatusCertifikata.POVUCEN){
			return "Sertifikat je povucen";
		}else if(status == StatusCertifikata.VALIDAN) {
			return "Sertifikat je validan";
		}else {
			return "Trazeni sertifikat ne postoji";
		}*/
		return status;
	}
	
	public CertificateViabilityModel newStatus(CertificateViabilityModel certificateViabilityModel) {
		List<CertificateViabilityModel> lista= certificateViabilityRepository.findAll();
		for (CertificateViabilityModel cvm : lista) {
			if(cvm.getSerijskiBroj().equals(certificateViabilityModel.getSerijskiBroj())) {
				return cvm;
			}
		}
		return certificateViabilityRepository.save(certificateViabilityModel);
	}
	
	public CertificateViabilityModel editStatus(BigInteger sn, StatusCertifikata status) {
		List<CertificateViabilityModel> lista= certificateViabilityRepository.findAll();
		for (CertificateViabilityModel cvm : lista) {
			if(cvm.getSerijskiBroj().equals(sn)) {
				cvm.setStatus(status);
				return certificateViabilityRepository.save(cvm);
			}
		}
		return certificateViabilityRepository.save(new CertificateViabilityModel(sn, status));
	}
}

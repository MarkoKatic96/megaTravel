package megatravel.bezbednost.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import megatravel.bezbednost.model.CertificateModel;

@Repository
public interface CertificateRepository extends JpaRepository<CertificateModel, Long> {
	@Query(value= "SELECT cert FROM CertificateModel cert WHERE cert.serijskiBroj = ?1")
	public CertificateModel findBySerijskiBroj(BigInteger serijskiBroj);
	
	public boolean existsBySerijskiBroj(BigInteger serijskiBroj);
	
	@Query(value= "SELECT DISTINCT cert FROM CertificateModel cert WHERE cert.nadcertifikat = ?1 ORDER BY cert.id ASC")
	public List<CertificateModel> findSubCertifikates(BigInteger serijskiBroj);
	
}

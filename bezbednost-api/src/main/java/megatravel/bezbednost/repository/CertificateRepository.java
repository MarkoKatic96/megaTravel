package megatravel.bezbednost.repository;

import java.math.BigInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import megatravel.bezbednost.model.CertificateModel;

@Repository
public interface CertificateRepository extends JpaRepository<CertificateModel, Long> {
	public CertificateModel findBySerijskiBroj(BigInteger serijskiBroj);
	
	public boolean existsBySerijskiBroj(BigInteger serijskiBroj);
	
	@Query(value= "SELECT DISTINCT cert FROM CertificateModel cert WHERE cert.nadcertifikat = ?1 ORDER BY cert.id ASC")
	public Page<CertificateModel> findSubCertifikates(BigInteger idCertifikata, Pageable pageable);
	
}

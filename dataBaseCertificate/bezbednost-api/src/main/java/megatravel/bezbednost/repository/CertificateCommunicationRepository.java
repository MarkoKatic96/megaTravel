package megatravel.bezbednost.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import megatravel.bezbednost.model.CertificateCommunicationModel;

@Repository
public interface CertificateCommunicationRepository extends JpaRepository<CertificateCommunicationModel, Long>{

	@Query(value= "SELECT DISTINCT comm FROM CertificateCommunicationModel comm WHERE (comm.serijskiBroj1 = ?1 AND comm.serijskiBroj2 = ?2) OR (comm.serijskiBroj2 = ?1 AND comm.serijskiBroj1 = ?2) ORDER BY comm.id ASC")
	public List<CertificateCommunicationModel> communicationApproved(BigInteger serijskiBroj1, BigInteger serijskiBroj2);

}

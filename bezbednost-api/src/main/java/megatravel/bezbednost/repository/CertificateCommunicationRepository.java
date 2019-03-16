package megatravel.bezbednost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import megatravel.bezbednost.model.CertificateCommunicationModel;

@Repository
public interface CertificateCommunicationRepository extends JpaRepository<CertificateCommunicationModel, Long>{

}

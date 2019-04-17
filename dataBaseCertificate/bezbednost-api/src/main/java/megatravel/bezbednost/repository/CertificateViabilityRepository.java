package megatravel.bezbednost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import megatravel.bezbednost.model.CertificateViabilityModel;

@Repository
public interface CertificateViabilityRepository extends JpaRepository<CertificateViabilityModel, Long>{

}

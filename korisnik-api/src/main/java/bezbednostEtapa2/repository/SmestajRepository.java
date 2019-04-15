package bezbednostEtapa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bezbednostEtapa2.model.Smestaj;

@Repository
public interface SmestajRepository extends JpaRepository<Smestaj, Long> {

	void delete(Long id);

	Smestaj findOne(Long id);

}

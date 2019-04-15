package bezbednostEtapa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bezbednostEtapa2.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

	Korisnik findOne(Long id);

	void delete(Long id);

	boolean existsByEmail(String email);

	Korisnik findByEmail(String email);

}

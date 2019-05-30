package io.webxml.korisnikservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import io.webxml.korisnikservice.model.Korisnik;

//@EnableJpaRepositories(basePackageClasses= {Korisnik.class})
@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{

	@Query(value = "SELECT * FROM Korisnik", nativeQuery = true)
	List<Korisnik> findAllKorisnici(); 
	
	@Query(value = "SELECT k FROM Korisnik k WHERE k.email = ?1")
	Korisnik nadjiKorisnikaPoEmail(String email);
}

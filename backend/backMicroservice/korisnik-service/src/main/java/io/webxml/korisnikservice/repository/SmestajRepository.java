package io.webxml.korisnikservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.webxml.korisnikservice.model.Smestaj;


//@EnableJpaRepositories(basePackageClasses= {Smestaj.class})
@Repository
public interface SmestajRepository extends JpaRepository<Smestaj, Long>{
	
	@Query(value = "SELECT * FROM Smestaj WHERE tip_smestaja = ?1", nativeQuery = true)
	List<Smestaj> findAllSmestajWithType(Long id);
	
	@Query(value = "SELECT * FROM Smestaj WHERE kategorija_smestaja = ?1", nativeQuery = true)
	List<Smestaj> findAllSmestajWithCategory(Long id);

}

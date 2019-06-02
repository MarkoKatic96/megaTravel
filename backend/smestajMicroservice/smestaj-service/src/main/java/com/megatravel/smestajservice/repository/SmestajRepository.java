package com.megatravel.smestajservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.megatravel.smestajservice.model.Smestaj;

@EnableJpaRepositories(basePackageClasses= {Smestaj.class})
@Repository
public interface SmestajRepository extends JpaRepository<Smestaj, Long> {
	
	@Query(value= "SELECT DISTINCT s FROM Smestaj s WHERE s.vlasnik = ?1 ORDER BY s.idSmestaja")
	Page<Smestaj> findAllFromMe(Long idVlasnika, Pageable page);
	
	@Query(value = "SELECT * FROM Smestaj", nativeQuery = true)
	List<Smestaj> pronadjiSve();
	
	@Query(value = "SELECT * FROM Smestaj WHERE tipsmestaja_id = ?1", nativeQuery = true)
	List<Smestaj> findAllSmestajWithType(Long id);
	
	@Query(value = "SELECT * FROM Smestaj WHERE kategorijasmestaja_id = ?1", nativeQuery = true)
	List<Smestaj> findAllSmestajWithCategory(Long id);
	
}

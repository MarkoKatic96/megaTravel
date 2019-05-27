package com.megatravel.agentglobalback.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.megatravel.agentglobalback.model.Smestaj;

@EnableJpaRepositories(basePackageClasses= {Smestaj.class})
@Repository
public interface SmestajRepository extends JpaRepository<Smestaj, Long> {
	
	@Query(value= "SELECT DISTINCT s FROM Smestaj s WHERE s.vlasnik = ?1 ORDER BY s.idSmestaja")
	Page<Smestaj> findAllFromMe(Long idVlasnika, Pageable page);

}

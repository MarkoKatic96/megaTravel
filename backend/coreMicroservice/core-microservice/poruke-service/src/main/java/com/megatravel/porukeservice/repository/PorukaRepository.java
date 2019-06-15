package com.megatravel.porukeservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.megatravel.porukeservice.model.Poruka;

@EnableJpaRepositories(basePackageClasses= {Poruka.class})
@Repository
public interface PorukaRepository extends JpaRepository<Poruka, Long> {
	
	/****************** AGENTSKE METODE ***********************/
	@Query(value= "SELECT DISTINCT p FROM Poruka p WHERE (p.posiljalac = ?1 AND p.primalac = ?2) OR (p.primalac = ?1 AND p.posiljalac = ?2) ORDER BY p.datumSlanja DESC")
	Page<Poruka> findAllWithUser(Long idKorisnika, Long idAgenta, Pageable pageable);
	
	@Query(value= "SELECT DISTINCT p FROM Poruka p WHERE (p.primalac = ?1 AND p.status = 'POSLATA') ORDER BY p.datumSlanja DESC")
	Page<Poruka> findAllNeprocitaneZaAgenta(Long idAgenta, Pageable pageable);

	@Query(value= "SELECT DISTINCT p FROM Poruka p WHERE (p.posiljalac = ?1 AND p.primalac = ?2 AND p.status = 'POSLATA') ORDER BY p.datumSlanja DESC")
	List<Poruka> findAllNeprocitaneWithUser(Long userId, Long agentId);
	
	
	/****************** KORISNICKE METODE ***********************/
	@Query(value= "SELECT DISTINCT p FROM Poruka p WHERE (p.posiljalac = ?1 AND p.primalac = ?2) OR (p.primalac = ?1 AND p.posiljalac = ?2) ORDER BY p.datumSlanja DESC")
	Page<Poruka> findAllWithAgent(Long idAgenta, Long idKorisnika, Pageable pageable);
	
	@Query(value= "SELECT DISTINCT p FROM Poruka p WHERE (p.primalac = ?1 AND p.status = 'POSLATA') ORDER BY p.datumSlanja DESC")
	Page<Poruka> findAllNeprocitaneZaKorisnika(Long userId, Pageable pageable);

	@Query(value= "SELECT DISTINCT p FROM Poruka p WHERE (p.posiljalac = ?1 AND p.primalac = ?2 AND p.status = 'POSLATA') ORDER BY p.datumSlanja DESC")
	List<Poruka> findAllNeprocitaneWithAgent(Long agentId, Long userId);
}

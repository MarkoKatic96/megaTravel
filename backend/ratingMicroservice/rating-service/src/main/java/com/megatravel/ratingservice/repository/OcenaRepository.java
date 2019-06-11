package com.megatravel.ratingservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.megatravel.ratingservice.model.Ocena;

@EnableJpaRepositories(basePackageClasses= {Ocena.class})
@Repository
public interface OcenaRepository extends JpaRepository<Ocena, Long> {

	@Query(value= "SELECT DISTINCT o FROM Ocena o WHERE (o.idSmestaj = ?1) ORDER BY o.timestamp DESC")
	Page<Ocena> findAllForSmestaj(Long idSmestaja, Pageable page);

	@Query(value= "SELECT avg(o.ocena) FROM Ocena o WHERE (o.idSmestaj = ?1)")
	float getAvgForSmestaj(Long idSmestaja);
}

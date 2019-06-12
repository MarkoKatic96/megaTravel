package com.megatravel.ratingservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.megatravel.ratingservice.model.Komentar;

@EnableJpaRepositories(basePackageClasses= {Komentar.class})
@Repository
public interface KomentarRepository extends JpaRepository<Komentar, Long> {

	@Query(value= "SELECT DISTINCT k FROM Komentar k WHERE (k.status = 'NEOBJAVLJEN') ORDER BY k.timestamp DESC")
	Page<Komentar> findAllNeobjavljenji(Pageable page);

	@Query(value= "SELECT DISTINCT k FROM Komentar k WHERE (k.status = 'OBJAVLJEN' AND k.idSmestaja=?1) ORDER BY k.timestamp DESC")
	Page<Komentar> findAllObjavljenjiForSmestaj(Long idSmestaja, Pageable page);

}

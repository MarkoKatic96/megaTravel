package io.webxml.reservationservice.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import io.webxml.reservationservice.model.SamostalnaRezervacija;

@EnableJpaRepositories(basePackageClasses= {SamostalnaRezervacija.class})
@Repository
public interface SamostalnaRezervacijaRepository extends JpaRepository<SamostalnaRezervacija, Long> {
	@Query(value= "SELECT r FROM SamostalnaRezervacija r WHERE (r.vlasnikId = ?1 AND r.smestajId = ?2) AND ((r.odDatuma <= ?3 AND r.doDatuma >= ?3) OR (r.odDatuma <= ?4 AND r.doDatuma >= ?4))")
	ArrayList<SamostalnaRezervacija> findKonfliktRezervacije(Long idAgenta, Long smestajId, Date odDatuma, Date doDatuma);

	List<SamostalnaRezervacija> findByVlasnikId(Long idAgenta);
}

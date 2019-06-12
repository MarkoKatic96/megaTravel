package io.webxml.reservationservice.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.webxml.reservationservice.model.Rezervacija;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long>{

	@Query(value= "SELECT DISTINCT r FROM Rezervacija r WHERE (r.updateTimestamp >= ?1 AND r.vlasnikId = ?2) ORDER BY r.rezervacijaId ASC")
	List<Rezervacija> findAllAfter(Date timestamp, Long idAgenta);

	@Query(value= "SELECT r FROM Rezervacija r WHERE (r.vlasnikId = ?1 AND r.smestajId = ?2) AND ((r.odDatuma <= ?3 AND r.doDatuma >= ?3) OR (r.odDatuma <= ?4 AND r.doDatuma >= ?4))")
	ArrayList<Rezervacija> findKonfliktRezervacije(Long idAgenta, Long smestajId, Date odDatuma, Date doDatuma);

}

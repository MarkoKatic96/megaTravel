package io.webxml.reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.webxml.reservationservice.model.Rezervacija;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long>{

}

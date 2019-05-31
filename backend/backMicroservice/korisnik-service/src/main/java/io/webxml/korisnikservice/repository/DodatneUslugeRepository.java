package io.webxml.korisnikservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.webxml.korisnikservice.model.DodatneUsluge;

@Repository
public interface DodatneUslugeRepository extends JpaRepository<DodatneUsluge, Long>{
	
}

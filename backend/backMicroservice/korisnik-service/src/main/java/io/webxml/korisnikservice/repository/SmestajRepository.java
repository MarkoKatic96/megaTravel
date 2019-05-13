package io.webxml.korisnikservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.webxml.korisnikservice.model.Smestaj;



@Repository
public interface SmestajRepository extends JpaRepository<Smestaj, Long>{

}

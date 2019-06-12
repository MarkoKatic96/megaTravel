package com.megatravel.smestajservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.megatravel.smestajservice.model.DodatneUsluge;

@Repository
public interface DodatnaUslugaRepository extends JpaRepository<DodatneUsluge, Long>{

	@Query(value = "SELECT * FROM dodatne_usluge", nativeQuery=true)
	List<DodatneUsluge> getAll();
	
}

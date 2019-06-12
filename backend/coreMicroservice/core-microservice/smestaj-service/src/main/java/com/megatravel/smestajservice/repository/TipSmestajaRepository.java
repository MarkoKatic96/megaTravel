package com.megatravel.smestajservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.megatravel.smestajservice.model.TipSmestaja;

@Repository
public interface TipSmestajaRepository extends JpaRepository<TipSmestaja, Long>{

	@Query(value = "SELECT id_tipa_smestaja, naziv_tipa_smestaja FROM tip_smestaja", nativeQuery=true)
	List<TipSmestaja> getAll();
	
}

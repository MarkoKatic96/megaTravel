package com.megatravel.smestajservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.megatravel.smestajservice.model.TipSmestaja;

@EnableJpaRepositories(basePackageClasses= {TipSmestaja.class})
@Repository
public interface TipSmestajaRepository extends JpaRepository<TipSmestaja, Long>{

	@Query(value = "SELECT * FROM tip_smestaja", nativeQuery=true)
	List<TipSmestaja> getAll();

	public TipSmestaja findByNazivTipaSmestaja(String nazivTipaSmestaja);
	
}

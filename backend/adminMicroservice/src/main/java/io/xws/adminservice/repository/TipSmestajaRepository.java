package io.xws.adminservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import io.xws.adminservice.model.Admin;
import io.xws.adminservice.model.Agent;
import io.xws.adminservice.model.TipSmestaja;

@EnableJpaRepositories(basePackageClasses = {TipSmestaja.class})
@Repository
public interface TipSmestajaRepository extends JpaRepository<TipSmestaja, Long> 
{
	public TipSmestaja findByNaziv(String naziv);
}

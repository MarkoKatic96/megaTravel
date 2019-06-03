package com.megatravel.smestajservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.smestajservice.model.DodatneUsluge;

@Repository
public interface DodatnaUslugaRepository extends JpaRepository<DodatneUsluge, Long>{

}

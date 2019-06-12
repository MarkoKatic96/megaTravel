package com.megatravel.agentlocalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.megatravel.agentlocalbackend.model.SamostalnaRezervacija;

@EnableJpaRepositories(basePackageClasses= {SamostalnaRezervacija.class})
@Repository
public interface SamostalnaRezervacijaRepository extends JpaRepository<SamostalnaRezervacija, Long> {

}

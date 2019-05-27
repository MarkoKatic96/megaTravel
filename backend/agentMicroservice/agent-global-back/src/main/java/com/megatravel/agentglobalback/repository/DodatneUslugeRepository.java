package com.megatravel.agentglobalback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.megatravel.agentglobalback.model.DodatneUsluge;

@EnableJpaRepositories(basePackageClasses= {DodatneUsluge.class})
@Repository
public interface DodatneUslugeRepository extends JpaRepository<DodatneUsluge, Long> {

}

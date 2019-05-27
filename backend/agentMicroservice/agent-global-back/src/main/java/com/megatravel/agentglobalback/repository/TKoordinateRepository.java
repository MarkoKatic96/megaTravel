package com.megatravel.agentglobalback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.megatravel.agentglobalback.model.TKoordinate;

@EnableJpaRepositories(basePackageClasses= {TKoordinate.class})
@Repository
public interface TKoordinateRepository extends JpaRepository<TKoordinate, Long> {
	
}

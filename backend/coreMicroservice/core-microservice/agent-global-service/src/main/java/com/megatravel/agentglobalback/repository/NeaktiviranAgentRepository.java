package com.megatravel.agentglobalback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.megatravel.agentglobalback.model.Agent;
import com.megatravel.agentglobalback.model.NeaktiviranAgent;

@EnableJpaRepositories(basePackageClasses= {NeaktiviranAgent.class})
@Repository
public interface NeaktiviranAgentRepository extends JpaRepository<NeaktiviranAgent, Long> {
	
	public NeaktiviranAgent findByEmail(String email);
	
	public NeaktiviranAgent findByPoslovniMaticniBroj(Long id);
	
	public boolean existsByEmail(String email);
	
	public boolean existsByPoslovniMaticniBroj(Long id);
	
	public Optional<NeaktiviranAgent> findById(Long id);
}
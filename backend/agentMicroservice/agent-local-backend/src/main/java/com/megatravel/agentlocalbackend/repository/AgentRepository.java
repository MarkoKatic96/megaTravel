package com.megatravel.agentlocalbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.megatravel.agentlocalbackend.model.Agent;

@EnableJpaRepositories(basePackageClasses= {Agent.class})
@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
	
	public Agent findByEmail(String email);
	
	public Agent findByPoslovniMaticniBroj(Long id);
	
	public boolean existsByEmail(String email);
	
	public boolean existsByPoslovniMaticniBroj(Long id);
	
	public Optional<Agent> findById(Long id);
}
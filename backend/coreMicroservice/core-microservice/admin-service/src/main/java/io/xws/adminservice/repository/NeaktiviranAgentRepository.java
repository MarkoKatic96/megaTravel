package io.xws.adminservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import io.xws.adminservice.model.NeaktiviranAgent;

@EnableJpaRepositories(basePackageClasses = {NeaktiviranAgent.class})
@Repository
public interface NeaktiviranAgentRepository extends JpaRepository<NeaktiviranAgent, Long> 
{
	public Optional<NeaktiviranAgent> findByEmail(String email);
	
	public boolean deleteByEmail(String email);
}

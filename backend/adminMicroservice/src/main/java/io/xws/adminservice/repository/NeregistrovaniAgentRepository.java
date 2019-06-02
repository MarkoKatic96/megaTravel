package io.xws.adminservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import io.xws.adminservice.model.Admin;
import io.xws.adminservice.model.NeregistrovaniAgent;

@EnableJpaRepositories(basePackageClasses = {NeregistrovaniAgent.class})
@Repository
public interface NeregistrovaniAgentRepository extends JpaRepository<NeregistrovaniAgent, Long> 
{
	public Optional<NeregistrovaniAgent> findByEmail(String email);
	
	public boolean deleteByEmail(String email);
}

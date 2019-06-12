package com.megatravel.agentlocalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.megatravel.agentlocalbackend.model.RevokedTokens;

@EnableJpaRepositories(basePackageClasses= {RevokedTokens.class})
@Repository
public interface RevokedTokensRepository extends JpaRepository<RevokedTokens, Long> {
	
	@Query(value= "SELECT t FROM RevokedTokens t WHERE t.token = ?1")
	RevokedTokens findOne(String token);

}

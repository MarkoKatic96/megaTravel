package io.xws.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.xws.adminservice.model.RevokedTokens;

@Repository
public interface RevokedTokensRepository extends JpaRepository<RevokedTokens, Long> 
{
	@Query(value= "SELECT t FROM RevokedTokens t WHERE t.token = ?1")
	RevokedTokens findOne(String token);
}

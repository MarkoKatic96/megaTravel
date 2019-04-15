package megatravel.agentlocal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import megatravel.agentlocal.model.AgentModel;

@Repository
public interface AgentRepository extends JpaRepository<AgentModel, Long> {
	public AgentModel findByEmail(String email);
	
	public boolean existsByEmail(String email);
}

package megatravel.agentlocal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import megatravel.agentlocal.model.SmestajModel;

@Repository
public interface SmestajRepository extends JpaRepository<SmestajModel, Long> {
	
	@Query(value= "SELECT s FROM SmestajModel s WHERE s.vlasnik.id = ?1")
	List<SmestajModel> findAll(Long id);
	
	
}
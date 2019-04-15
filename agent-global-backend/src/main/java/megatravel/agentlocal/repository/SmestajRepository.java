package megatravel.agentlocal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import megatravel.agentlocal.model.SmestajModel;

@Repository
public interface SmestajRepository extends JpaRepository<SmestajModel, Long> {

}
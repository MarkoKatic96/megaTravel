package bezbednost.etapa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bezbednost.etapa2.model.Servis;

@Repository
public interface ServisRepository extends JpaRepository<Servis, Long>{

}

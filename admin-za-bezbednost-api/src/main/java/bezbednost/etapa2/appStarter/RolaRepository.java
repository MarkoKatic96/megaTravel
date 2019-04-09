package bezbednost.etapa2.appStarter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bezbednost.etapa2.model.Rola;

@Repository
public interface RolaRepository extends JpaRepository<Rola, Long>{

}

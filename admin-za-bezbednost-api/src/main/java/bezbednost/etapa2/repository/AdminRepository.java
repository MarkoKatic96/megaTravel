package bezbednost.etapa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bezbednost.etapa2.model.Korisnik;

@Repository
public interface AdminRepository extends JpaRepository<Korisnik, Long>{

}

package megatravel.bezbednost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import megatravel.bezbednost.model.AdminModel;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Long> {
	public AdminModel findByEmail(String email);
	
	public boolean existsByEmail(String email);
}

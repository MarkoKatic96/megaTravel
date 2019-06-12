package io.xws.adminservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import io.xws.adminservice.model.Admin;

@EnableJpaRepositories(basePackageClasses = {Admin.class})
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>
{
	public Optional<Admin> findByEmail(String email);

	public boolean existsByEmail(String email);
}

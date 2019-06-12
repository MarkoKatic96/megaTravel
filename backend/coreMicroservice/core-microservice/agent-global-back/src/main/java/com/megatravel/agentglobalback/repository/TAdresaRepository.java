package com.megatravel.agentglobalback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.megatravel.agentglobalback.model.TAdresa;

@EnableJpaRepositories(basePackageClasses= {TAdresa.class})
@Repository
public interface TAdresaRepository extends JpaRepository<TAdresa, Long> {

}

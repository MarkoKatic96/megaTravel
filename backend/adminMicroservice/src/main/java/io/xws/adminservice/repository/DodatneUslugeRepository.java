package io.xws.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.xws.adminservice.model.DodatneUsluge;

@Repository
public interface DodatneUslugeRepository extends JpaRepository<DodatneUsluge, Long>
{

}

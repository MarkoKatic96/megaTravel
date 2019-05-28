package io.xws.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.xws.adminservice.model.Smestaj;

@Repository
public interface SmestajRepository extends JpaRepository<Smestaj, Long> {

}

package io.xws.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.xws.adminservice.model.Komentar;

@Repository
public interface KomentarRepository extends JpaRepository<Komentar, Long> {

}

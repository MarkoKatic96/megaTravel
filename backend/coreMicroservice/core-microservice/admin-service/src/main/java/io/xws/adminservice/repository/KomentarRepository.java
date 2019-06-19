package io.xws.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import io.xws.adminservice.model.Komentar;

@EnableJpaRepositories(basePackageClasses = {Komentar.class})
@Repository
public interface KomentarRepository extends JpaRepository<Komentar, Long> {

}

package io.xws.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.xws.adminservice.model.KategorijaSmestaja;

@Repository
public interface KategorijaSmestajaRepository extends JpaRepository<KategorijaSmestaja, Long>
{

}

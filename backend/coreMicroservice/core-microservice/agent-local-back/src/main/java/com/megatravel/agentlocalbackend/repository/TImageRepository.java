package com.megatravel.agentlocalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.megatravel.agentlocalbackend.model.TImage;

@EnableJpaRepositories(basePackageClasses= {TImage.class})
@Repository
public interface TImageRepository extends JpaRepository<TImage, Long> {

}

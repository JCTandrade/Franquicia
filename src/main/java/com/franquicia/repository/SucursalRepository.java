package com.franquicia.repository;

import com.franquicia.entities.FranquiciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.franquicia.entities.SucursalEntity;

import java.util.List;

@Repository
public interface SucursalRepository extends JpaRepository<SucursalEntity, Long> {

    List<SucursalEntity>findByFranquicia(FranquiciaEntity franquiciaEntity);

}
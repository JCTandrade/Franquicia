package com.franquicia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.franquicia.entities.SucursalEntity;

@Repository
public interface SucursalRepository extends JpaRepository<SucursalEntity, Long> {

}

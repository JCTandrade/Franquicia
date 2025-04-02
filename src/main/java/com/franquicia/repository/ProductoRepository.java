package com.franquicia.repository;

import com.franquicia.entities.SucursalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.franquicia.entities.ProductoEntity;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    List<ProductoEntity> findBySucursal(SucursalEntity sucursal);
}

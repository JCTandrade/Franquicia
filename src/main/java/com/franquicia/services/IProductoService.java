package com.franquicia.services;

import com.franquicia.dto.ProductoRequestDto;
import com.franquicia.dto.ResponseGenerico;
import com.franquicia.entities.ProductoEntity;
import com.franquicia.entities.SucursalEntity;

import java.util.List;

public interface IProductoService {
    ResponseGenerico guardar(ProductoRequestDto productoRequestDto);

    ResponseGenerico actualizarProducto(Long id, ProductoRequestDto productoRequestDto);

    ProductoEntity buscarPorIdEntity(Long id);

    ResponseGenerico eliminar(Long id);

    List<ProductoEntity> findBySucursal(SucursalEntity sucursal);
}

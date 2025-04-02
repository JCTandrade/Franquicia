package com.franquicia.services;

import com.franquicia.dto.ProductoRequestDto;
import com.franquicia.dto.ResponseGenerico;
import com.franquicia.entities.ProductoEntity;

public interface IProductoService {
    ResponseGenerico guardar(ProductoRequestDto productoRequestDto);

    ResponseGenerico actualizarProducto(Long id, ProductoRequestDto productoRequestDto);

    ProductoEntity buscarPorIdEntity(Long id);

    ResponseGenerico eliminar(Long id);
}

package com.franquicia.services;

import com.franquicia.dto.ResponseGenerico;
import com.franquicia.dto.SucursalRequestDto;
import com.franquicia.entities.SucursalEntity;

public interface ISucursalService {
    ResponseGenerico guardar(SucursalRequestDto sucursalRequestDto);

    ResponseGenerico actualizarSucursal(Long id, SucursalRequestDto sucursalRequestDto);

    SucursalEntity buscarPorIdEntity(Long id);
}

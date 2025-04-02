package com.franquicia.services;

import com.franquicia.dto.ResponseGenerico;
import com.franquicia.dto.SucursalRequestDto;
import com.franquicia.entities.FranquiciaEntity;
import com.franquicia.entities.SucursalEntity;

import java.util.List;

public interface ISucursalService {
    ResponseGenerico guardar(SucursalRequestDto sucursalRequestDto);

    ResponseGenerico actualizarSucursal(Long id, SucursalRequestDto sucursalRequestDto);

    SucursalEntity buscarPorIdEntity(Long id);

    List<SucursalEntity> buscarPorFranquicia(FranquiciaEntity franquiciaEntity);
}
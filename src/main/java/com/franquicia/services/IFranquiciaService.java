package com.franquicia.services;

import com.franquicia.dto.ResponseGenerico;
import com.franquicia.entities.FranquiciaEntity;
import com.franquicia.dto.FranquiciaRequestDto;

public interface IFranquiciaService {

    ResponseGenerico guardar (FranquiciaRequestDto franquiciaRequestDto);
    ResponseGenerico actualizar(Long id,FranquiciaRequestDto franquiciaRequestDto);

    FranquiciaEntity buscarPorID(Long id);
}

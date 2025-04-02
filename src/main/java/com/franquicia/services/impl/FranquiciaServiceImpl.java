package com.franquicia.services.impl;

import com.franquicia.Enum.EstadosEnum;
import com.franquicia.constant.Constant;
import com.franquicia.dto.ResponseGenerico;
import com.franquicia.dto.FranquiciaRequestDto;
import com.franquicia.entities.FranquiciaEntity;
import com.franquicia.repository.FranquiciaRepository;
import com.franquicia.services.IFranquiciaService;
import com.franquicia.util.GeneradorRespuesta;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class FranquiciaServiceImpl implements IFranquiciaService {

    FranquiciaRepository franquiciaRepository;
    private final GeneradorRespuesta generadorRespuesta;

    @Override
    public ResponseGenerico guardar(FranquiciaRequestDto franquiciaRequestDto) {
        FranquiciaEntity franquiciaEntity = new FranquiciaEntity();
        franquiciaEntity.setDescripcion(franquiciaRequestDto.getDescripcion());
        franquiciaEntity.setNombre(franquiciaRequestDto.getNombre());

        FranquiciaEntity entity = this.franquiciaRepository.save(franquiciaEntity);

        if (franquiciaEntity.getIdFranquicia() == null) {
            return this.generadorRespuesta.generarRespuesta(HttpStatus.BAD_REQUEST, EstadosEnum.ERROR, Constant.Message.ERROR_CREANDO,Constant.Message.ERROR_CREANDO);
        }
        return this.generadorRespuesta.generarRespuesta(HttpStatus.OK,EstadosEnum.SUCCESS,Constant.Message.FRANQUICIA_GUARDADA,entity);
    }

    @Override
    public ResponseGenerico actualizar(Long id, FranquiciaRequestDto franquiciaRequestDto) {
        if (!Objects.equals(id, franquiciaRequestDto.getId())){
                return this.generadorRespuesta.noCoincide();
            }
        FranquiciaEntity franquiciaEntity = this.buscarPorID(id);
        if (franquiciaEntity == null){
            return this.generadorRespuesta.noExiste();
        }

        franquiciaEntity.setNombre(franquiciaRequestDto.getNombre());

        FranquiciaEntity franquiciaActualizada = this.franquiciaRepository.save(franquiciaEntity);

        return this.generadorRespuesta.generarRespuesta(HttpStatus.OK,EstadosEnum.SUCCESS,Constant.Message.FRANQUICIA_ACTUALIZADA,franquiciaActualizada);
    }

    @Override
    public FranquiciaEntity buscarPorID(Long id) {
        return this.franquiciaRepository.findById(id).orElse(null);
    }
}

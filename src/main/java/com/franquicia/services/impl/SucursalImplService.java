package com.franquicia.services.impl;
 
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.franquicia.Enum.EstadosEnum;
import com.franquicia.constant.Constant;
import com.franquicia.dto.ResponseGenerico;
import com.franquicia.dto.SucursalRequestDto;
import com.franquicia.entities.FranquiciaEntity;
import com.franquicia.entities.SucursalEntity;
import com.franquicia.repository.SucursalRepository;
import com.franquicia.services.IFranquiciaService;
import com.franquicia.services.ISucursalService;
import com.franquicia.util.GeneradorRespuesta;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class SucursalImplService implements ISucursalService {

    SucursalRepository sucursalRepository;
    IFranquiciaService franquiciaService;

    private final GeneradorRespuesta generadorRespuesta;

    @Override
    public ResponseGenerico guardar(SucursalRequestDto sucursalRequestDto) {
        FranquiciaEntity franquiciaEntity = this.franquiciaService.buscarPorID(sucursalRequestDto.getIdFranquicia());
        if (franquiciaEntity == null) {
            return this.generadorRespuesta.generarRespuesta(HttpStatus.OK, EstadosEnum.SUCCESS,
                    Constant.Message.FRANQUICIA_NO_EXISTE, null);
        }

        SucursalEntity sucursalEntity = new SucursalEntity();
        sucursalEntity.setFranquicia(franquiciaEntity);
        sucursalEntity.setNombre(sucursalRequestDto.getNombre());
        sucursalEntity.setTelefono(sucursalRequestDto.getTelefono());

        SucursalEntity sucursal = this.sucursalRepository.save(sucursalEntity);

        return this.generadorRespuesta.generarRespuesta(HttpStatus.OK, EstadosEnum.SUCCESS,
                Constant.Message.OPERACION_EXITO, sucursal);

    }

    @Override
    public ResponseGenerico actualizarSucursal(Long idSucursal, SucursalRequestDto sucursalRequestDto) {
        try {
            validarIdSucursal(idSucursal, sucursalRequestDto.getIdSucursal());

            SucursalEntity sucursalExistente = obtenerSucursalPorId(idSucursal);

            validarExistenciaSucursal(sucursalExistente);

            validarFranquicia(sucursalExistente, sucursalRequestDto.getIdFranquicia());

            SucursalEntity sucursalActualizada = construirSucursalActualizada(sucursalExistente, sucursalRequestDto);

            SucursalEntity sucursalGuardada = sucursalRepository.save(sucursalActualizada);

            return generadorRespuesta.generarRespuesta(HttpStatus.OK, EstadosEnum.SUCCESS,
                    Constant.Message.OPERACION_EXITO, sucursalGuardada);

        } catch (Exception e) {
            log.error("Error al actualizar la sucursal con el ID: {} con error : {}", idSucursal, e.getMessage());
            return generadorRespuesta.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, EstadosEnum.ERROR,
                    Constant.Message.ERROR_CONSULTADO.replace("%s", "sucursal"), null);
        }
    }

    private void validarIdSucursal(Long idSucursal, Long idSucursalDto) {
        if (!Objects.equals(idSucursal, idSucursalDto)) {
            throw new IllegalArgumentException(generadorRespuesta.noCoincide().getMessage());
        }
    }

    private SucursalEntity obtenerSucursalPorId(Long idSucursal) {
        return buscarPorIdEntity(idSucursal);
    }

    private void validarExistenciaSucursal(SucursalEntity sucursal) {
        if (sucursal == null) {
            throw new NoSuchElementException(generadorRespuesta.noExiste().getMessage());
        }
    }

    private void validarFranquicia(SucursalEntity sucursal, Long idFranquiciaDto) {
        if (!Objects.equals(sucursal.getFranquicia().getIdFranquicia(), idFranquiciaDto)) {
            throw new IllegalArgumentException(generadorRespuesta.generarRespuesta(HttpStatus.BAD_REQUEST, EstadosEnum.ERROR,
                    Constant.Message.NO_CAMBIAR, null).getMessage());
        }
    }

    private SucursalEntity construirSucursalActualizada(SucursalEntity sucursalExistente, SucursalRequestDto sucursalRequestDto) {
        return sucursalExistente.toBuilder()
                .nombre(sucursalRequestDto.getNombre())
                .telefono(sucursalRequestDto.getTelefono())
                .build();
    }

    @Override
    public SucursalEntity buscarPorIdEntity(Long id) {
        return this.sucursalRepository.findById(id).orElse(null);
    }

}

package com.franquicia.services.impl;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.franquicia.Enum.EstadosEnum;
import com.franquicia.constant.Constant;
import com.franquicia.dto.ProductoRequestDto;
import com.franquicia.dto.ResponseGenerico;
import com.franquicia.entities.ProductoEntity;
import com.franquicia.entities.SucursalEntity;
import com.franquicia.repository.ProductoRepository;
import com.franquicia.services.IProductoService;
import com.franquicia.services.ISucursalService;
import com.franquicia.util.GeneradorRespuesta;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ProductoServiceImpl implements IProductoService {

    ISucursalService iSucursalService;
    ProductoRepository productoRepository;
    private final GeneradorRespuesta generadorRespuesta;

    @Override
    public ResponseGenerico guardar(ProductoRequestDto productoRequestDto) {
        try {
            SucursalEntity sucursalExistente = this.obtenerSucursalPorId(productoRequestDto.getIdSucursal());
            validarExistenciaSucursal(sucursalExistente);

            ProductoEntity productoEntity = new ProductoEntity();
            productoEntity.setCantidadStock(productoRequestDto.getCantidadStock());
            productoEntity.setNombre(productoRequestDto.getNombre());
            productoEntity.setSucursal(sucursalExistente);

            ProductoEntity entity = this.productoRepository.save(productoEntity);

            if (entity.getProductoID() == null) {
                return this.generadorRespuesta.generarRespuesta(HttpStatus.BAD_REQUEST, EstadosEnum.ERROR,
                        Constant.Message.ERROR_CREANDO, Constant.Message.ERROR_CREANDO_PRODUCTO);
            }
            return this.generadorRespuesta.generarRespuesta(HttpStatus.OK, EstadosEnum.SUCCESS,
                    Constant.Message.OPERACION_EXITO, entity);
        } catch (Exception e) {
            log.error("Error al guardar el productocon error : {}", e.getMessage());
            return generadorRespuesta.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, EstadosEnum.ERROR,
                    Constant.Message.ERROR_CREANDO_PRODUCTO, null);
        }
    }

    @Override
    public ResponseGenerico actualizarProducto(Long id, ProductoRequestDto productoRequestDto) {
        try {
            validarIdProducto(id, productoRequestDto.getIdProducto());
            ProductoEntity buscarProducto = this.buscarPorIdEntity(id);

            ProductoEntity productoActualizado = this.productoRepository
                    .save(construirProductoActualizada(buscarProducto, productoRequestDto));

            return generadorRespuesta.generarRespuesta(HttpStatus.OK, EstadosEnum.SUCCESS,
                    Constant.Message.OPERACION_EXITO, productoActualizado);

        } catch (Exception e) {
            log.error("Error al actualizar el producto con el ID: {} con error : {}", id, e.getMessage());
            return generadorRespuesta.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, EstadosEnum.ERROR,
                    Constant.Message.ERROR_CONSULTADO.replace("%s", "producto"), e.getMessage());
        }
    }

    @Override
    public ProductoEntity buscarPorIdEntity(Long id) {
        return this.productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(generadorRespuesta.noExiste().getMessage()));
    }


    private void validarIdProducto(Long idSucursal, Long idSucursalDto) {
        if (!Objects.equals(idSucursal, idSucursalDto)) {
            throw new IllegalArgumentException(generadorRespuesta.noCoincide().getMessage());
        }
    }

    private SucursalEntity obtenerSucursalPorId(Long idSucursal) {
        return this.iSucursalService.buscarPorIdEntity(idSucursal);
    }

    private void validarExistenciaSucursal(SucursalEntity sucursal) {
        if (sucursal == null) {
            throw new NoSuchElementException(generadorRespuesta.noExiste().getMessage());
        }
    }

    private ProductoEntity construirProductoActualizada(ProductoEntity productoEntity, ProductoRequestDto productoRequestDto) {
        return productoEntity.toBuilder()
                .nombre(productoRequestDto.getNombre())
                .cantidadStock(productoRequestDto.getCantidadStock())
                .build();
    }

    @Override
    public ResponseGenerico eliminar(Long id) {
        try {
            this.productoRepository.deleteById(id);
            return this.generadorRespuesta.generarRespuesta(HttpStatus.OK, EstadosEnum.SUCCESS,
            Constant.Message.OPERACION_EXITO, null);
        } catch (Exception e) {
            log.error("Error al eliminar el producto con el ID: {} con error : {}", id, e.getMessage());
            return generadorRespuesta.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, EstadosEnum.ERROR,
            Constant.Message.ERROR_CONSULTADO.replace("%s", "producto"), e.getMessage());
        }
       
    }
}

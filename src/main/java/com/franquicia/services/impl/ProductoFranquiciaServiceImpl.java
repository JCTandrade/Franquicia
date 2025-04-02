package com.franquicia.services.impl;

import com.franquicia.Enum.EstadosEnum;
import com.franquicia.constant.Constant;
import com.franquicia.dto.ProductoStockSucursalDto;
import com.franquicia.dto.ResponseGenerico;
import com.franquicia.entities.FranquiciaEntity;
import com.franquicia.entities.SucursalEntity;
import com.franquicia.services.IFranquiciaService;
import com.franquicia.services.IProductoFranquiciaService;
import com.franquicia.services.IProductoService;
import com.franquicia.services.ISucursalService;
import com.franquicia.util.GeneradorRespuesta;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductoFranquiciaServiceImpl implements IProductoFranquiciaService {



        private IFranquiciaService franquiciaService;

        private ISucursalService sucursalService;


        private IProductoService productoService;

        private GeneradorRespuesta generadorRespuesta;

        @Override
        public ResponseGenerico obtenerSucursalesPorFranquicia(Long id) {
            FranquiciaEntity franquicia = obtenerFranquiciaPorId(id);
            List<SucursalEntity> sucursales = buscarSucursalesPorFranquicia(franquicia);
            List<ProductoStockSucursalDto> productos = obtenerProductosMayorStockPorSucursal(sucursales, franquicia.getNombre());

            return generadorRespuesta.generarRespuesta(HttpStatus.OK, EstadosEnum.SUCCESS,
                    Constant.Message.OPERACION_EXITO, productos);
        }

        private FranquiciaEntity obtenerFranquiciaPorId(Long id) {
            return Optional.ofNullable(franquiciaService.buscarPorID(id))
                    .orElseThrow(() -> new NoSuchElementException(generadorRespuesta.noExiste().getMessage()));
        }

        private List<SucursalEntity> buscarSucursalesPorFranquicia(FranquiciaEntity franquicia) {
            return Optional.ofNullable(sucursalService.buscarPorFranquicia(franquicia))
                    .orElseThrow(() -> new IllegalArgumentException("No existen sucursales para la franquicia"));
        }

        private List<ProductoStockSucursalDto> obtenerProductosMayorStockPorSucursal(List<SucursalEntity> sucursales, String nombreFranquicia) {
            return sucursales.stream()
                    .map(sucursal -> obtenerProductoMayorStockDto(sucursal, nombreFranquicia))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }

        private Optional<ProductoStockSucursalDto> obtenerProductoMayorStockDto(SucursalEntity sucursal, String nombreFranquicia) {
            return this.productoService.findBySucursal(sucursal).stream()
                    .max((p1, p2) -> Integer.compare(p1.getCantidadStock(), p2.getCantidadStock()))
                    .map(producto -> {
                        ProductoStockSucursalDto dto = new ProductoStockSucursalDto();
                        dto.setNombreFranquicia(nombreFranquicia);
                        dto.setNombreSucursal(sucursal.getNombre());
                        dto.setNombreProducto(producto.getNombre());
                        dto.setCantidadStock(producto.getCantidadStock());
                        return dto;
                    });
        }
    }



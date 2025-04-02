package com.franquicia.dto;

import lombok.Data;

@Data
public class ProductoStockSucursalDto {

    private String nombreFranquicia;
    private String nombreSucursal;
    private String nombreProducto;
    private int cantidadStock;
}

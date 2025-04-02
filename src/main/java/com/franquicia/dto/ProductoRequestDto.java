package com.franquicia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductoRequestDto {

    private Long idProducto;

    @NotNull(message = "El ID de la sucursal no puede ser nulo")
    @Schema(description = "ID de la sucursal.", example = "34")
    private Long idSucursal;

    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Schema(description = "Nombre del producto.", example = "Producto TEST")
    private String nombre;


   
    @Min(value = 0, message = "La cantidad de stock debe ser un número entero positivo.")
    @Schema(description = "Cantidad de stock del producto.", example = "50")
    private int cantidadStock;
    
}

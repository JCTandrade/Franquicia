package com.franquicia.dto;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SucursalRequestDto {

    @NotNull(message = "El ID de la franquicia no puede ser nulo")
    @Schema(description = "ID de la franquicia.", example = "34")
    private Long idFranquicia;

    private Long idSucursal;

    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Schema(description = "Nombre de la sucursal.", example = "Almacen exito S.A")
    private String nombre;

    @NotBlank(message = "El número de teléfono no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "El número de teléfono debe contener solo números")
    @Size(min = 1, max = 15, message = "El número de teléfono debe tener entre 10 y 15 dígitos")
    @Schema(description = "Telefono de la franquicia.", example = "0018002882")
    private String telefono;
}

package com.franquicia.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franquicia.dto.ResponseGenerico;
import com.franquicia.dto.SucursalRequestDto;
import com.franquicia.services.ISucursalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;



@RestController
@RequestMapping(value = "v1/sucursal", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sucursal", description = "Operaciones relacionadas con la gestion de sucursal")
@AllArgsConstructor
public class SucursalController {

    ISucursalService sucursalService;

    @PostMapping("/guardar")
    @Operation(summary = "Método que permite la creación de una nueva sucursal.", description = "Devuelve objecto generico con la sucursal creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se crea de manera correcta la sucursal.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenerico.class), examples = @ExampleObject(name = "example1", value = "{\"status\":\"OK\",\"estadoOperacion\":\"SUCCESS\","
                    +
                    "\"message\":\"La operacion se realizado de manera correcta\",\"data\":{\"idSucursal\":3," +
                    "\"nombre\":\"Pinturas Cali S.A\",\"responsable\":\"Pedro tovar\",\"direccion\":\"Calle 40 # 31-3A\","
                    +
                    "\"telefono\":\"0018002882\",\"email\":\"taller@gmail.com\",\"createdAt\":\"2024-08-27\",\"updatedAt\":null,"
                    +
                    "\"estadoRegistro\":\"ACTIVO\",\"compania\":{\"idCompania\":1,\"nombre\":\"Empresas olimpica S.A\","
                    +
                    "\"nit\":\"123456-7363\",\"propietario\":\"Carlos mario Tovar\",\"direccion\":\"Calle 40 # 31-3A\","
                    +
                    "\"telefono\":\"0018002882\",\"email\":\"Olimpica@gmail.com\",\"createdAt\":\"2024-08-27\"," +
                    "\"updatedAt\":null,\"estadoRegistro\":\"ACTIVO\"}}}")))
    })
    public ResponseEntity<ResponseGenerico> guardar(@Valid @RequestBody SucursalRequestDto sucursalRequestDto) {
        ResponseGenerico servicio = this.sucursalService.guardar(sucursalRequestDto);
        return new ResponseEntity<>(servicio, servicio.getStatus());
    }

    @PutMapping("actualizar/{id}")
    @Operation(summary = "Actualizar la sucursal", description = "Método que permite actualizar la sucursal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal actualizada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenerico.class), examples = @ExampleObject(name = "example1", value = "{\"status\":\"OK\",\"estadoOperacion\":\"SUCCESS\","
                    +
                    "\"message\":\"La operacion se realizado de manera correcta\"," +
                    "\"data\":{\"companiaDto\":{\"idCompania\":1,\"nombre\":\"Empresas olimpica S.A\"}," +
                    "\"idSucursal\":2,\"nombre\":\"Taller Cali S.A\",\"responsable\":\"Pedro tovar\"," +
                    "\"direccion\":\"Calle 40 # 31-3A\",\"telefono\":\"0018002882\"," +
                    "\"email\":\"taller@gmail.com\",\"estadoRegistro\":\"ACTIVO\"}}"))),
            @ApiResponse(responseCode = "400", description = "La sucursal con el ID especificado y el ID del body no coincide.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenerico.class), examples = @ExampleObject(name = "example1", value = "{\"status\":\"BAD_REQUEST\",\"estadoOperacion\":\"ERROR\","
                    +
                    "\"message\":\"Error: la información suministrada no coincide.\",\"data\":null}"))

            )
    })
    public ResponseEntity<ResponseGenerico> actualizar(@PathVariable Long id,
            @Valid @RequestBody SucursalRequestDto sucursalRequestDto) {
        ResponseGenerico servicio = this.sucursalService.actualizarSucursal(id, sucursalRequestDto);
        return new ResponseEntity<>(servicio, servicio.getStatus());
    }

}

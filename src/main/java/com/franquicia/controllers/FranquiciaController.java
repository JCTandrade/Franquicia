package com.franquicia.controllers;


import com.franquicia.dto.ResponseGenerico;
import com.franquicia.dto.FranquiciaRequestDto;
import com.franquicia.services.IFranquiciaService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/franquicia", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Franquicia", description = "Operaciones relacionadas con las franquicia")
@AllArgsConstructor
public class FranquiciaController {

    IFranquiciaService iFranquiciaService;

    @PostMapping("/guardar")
    @Operation(summary = "Método que permite la creación de una nueva franquicia.", description = "Devuelve objecto generico con la franquicia creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se crea de manera correcta la franquicia.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseGenerico.class),
                            examples = @ExampleObject(name = "example1", value = "{\r\n" + //
                                                                        "    \"status\": \"OK\",\r\n" + //
                                                                        "    \"estadoOperacion\": \"SUCCESS\",\r\n" + //
                                                                        "    \"message\": \"Se ha guardado la franquicia\",\r\n" + //
                                                                        "    \"data\": {\r\n" + //
                                                                        "        \"idFranquicia\": 1,\r\n" + //
                                                                        "        \"nombre\": \"Nueva franquicia desde postman\",\r\n" + //
                                                                        "        \"descripcion\": \"Franquicia creada desde postman esto es una descripcion\"\r\n" + //
                                                                        "    }\r\n" + //
                                                                        "}"
                            )))
    })
    public ResponseEntity<ResponseGenerico> guardar(@Valid @RequestBody FranquiciaRequestDto franquiciaRequestDto) {
        ResponseGenerico servicio = this.iFranquiciaService.guardar(franquiciaRequestDto);
        return new ResponseEntity<>(servicio, servicio.getStatus());
    }

   

    @PutMapping("actualizar/{id}")
    @Operation(summary = "Actualizar la franquicia", description = "Método que permite actualizar la franquicia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "franquicia actualizada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseGenerico.class),
                            examples = @ExampleObject(name = "example1", value = "{\"status\":\"OK\",\"estadoOperacion\":\"SUCCESS\"," +
                                    "\"message\":\"Se ha actualizado de manera correcta.\"," +
                                    "\"data\":{\"nombre\":\"Empresas olimpica S.A\",\"nit\":\"123456-7363\"," +
                                    "\"propietario\":\"Carlos mario Tovar\",\"direccion\":\"Calle 40 # 31-3A\"," +
                                    "\"telefono\":\"0018002882\",\"email\":\"Olimpica@gmail.com\",\"estadoRegistro\":ACTIVO}}"
                            ))),
            @ApiResponse(responseCode = "400", description = "La franquicia con el ID especificado y el ID del body no coincide.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseGenerico.class),
                            examples = @ExampleObject(name = "example1", value = "{\"status\":\"BAD_REQUEST\",\"estadoOperacion\":\"ERROR\"," +
                                    "\"message\":\"Error: la información suministrada no coincide.\",\"data\":null}"
                            )
                    )

            )
    })
    public ResponseEntity<ResponseGenerico> actualizar(@PathVariable Long id, @Valid @RequestBody FranquiciaRequestDto franquiciaRequestDto) {
        ResponseGenerico servicio = this.iFranquiciaService.actualizar(id, franquiciaRequestDto);
        return new ResponseEntity<>(servicio, servicio.getStatus());
    }
}

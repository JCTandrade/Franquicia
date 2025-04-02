package com.franquicia.controllers;

import com.franquicia.dto.ResponseGenerico;
import com.franquicia.services.IProductoFranquiciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/producto-franquicia", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Producto franquicia", description = "Operaciones relacionadas con la gestion de productos y sus franquicias")
@AllArgsConstructor
public class ProductoFranquiciaController {

    IProductoFranquiciaService iProductoFranquiciaService;

    @GetMapping("buscar-producto/{id}")
    @Operation(summary = "Método que permite obtener los producto", description = "Método que permite consultar los productos asociados a una franquicia en especifico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal actualizada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenerico.class), examples = @ExampleObject(name = "example1", value = "{\"status\":\"OK\",\"estadoOperacion\":\"SUCCESS\",\"message\":\"La operacion se realizado de manera correcta\",\"data\":{\"idSucursal\":1,\"nombre\":\"Actualizar sucursal desde postman 1232\",\"telefono\":\"00011133\"}}"))),
            @ApiResponse(responseCode = "400", description = "La sucursal con el ID especificado y el ID del body no coincide.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenerico.class), examples = @ExampleObject(name = "example1", value = "{\"status\":\"BAD_REQUEST\",\"estadoOperacion\":\"ERROR\","
                    +
                    "\"message\":\"Error: la información suministrada no coincide.\",\"data\":null}"))

            )
    })
    ResponseEntity<ResponseGenerico> buscar(@PathVariable Long id) {
        ResponseGenerico servicio = this.iProductoFranquiciaService.obtenerSucursalesPorFranquicia(id);
        return new ResponseEntity<>(servicio, servicio.getStatus());
    }
}

package com.franquicia.controllers;

import com.franquicia.dto.ProductoRequestDto;
import com.franquicia.dto.ResponseGenerico;
import com.franquicia.services.IProductoService;

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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/producto", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Producto", description = "Operaciones relacionadas con la gestion de producto")
@AllArgsConstructor
public class ProductoController {

    IProductoService iProductoService;

    @PostMapping("/guardar")
    @Operation(summary = "Método que permite la creación de un nuevo producto.", description = "Devuelve objecto generico con la producto creado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se crea de manera correcta el producto.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenerico.class), examples = @ExampleObject(name = "example1", value = "{\"status\":\"OK\",\"estadoOperacion\":\"SUCCESS\",\"message\":\"Se ha guardado la franquicia\",\"data\":{\"productoID\":1,\"nombre\":\"Producto desde postman\",\"cantidadStock\":12,\"producto\":{\"idproducto\":1,\"nombre\":\"Guardar producto desde postman\",\"telefono\":\"3223008828\"}}}")))
    })
    public ResponseEntity<ResponseGenerico> guardar(@Valid @RequestBody ProductoRequestDto productoRequestDto) {
        ResponseGenerico servicio = this.iProductoService.guardar(productoRequestDto);
        return new ResponseEntity<>(servicio, servicio.getStatus());
    }

    @PutMapping("actualizar/{id}")
    @Operation(summary = "Actualizar el producto", description = "Método que permite actualizar la producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "producto actualizado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenerico.class), examples = @ExampleObject(name = "example1", value = "{\"status\":\"OK\",\"estadoOperacion\":\"SUCCESS\",\"message\":\"La operacion se realizado de manera correcta\",\"data\":{\"productoID\":1,\"nombre\":\"Producto desde postman actualizado\",\"cantidadStock\":12,\"producto\":{\"idproducto\":1,\"nombre\":\"Guardar producto desde postman\",\"telefono\":\"3223008828\"}}}"))),
            @ApiResponse(responseCode = "400", description = "La producto con el ID especificado y el ID del body no coincide.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenerico.class), examples = @ExampleObject(name = "example1", value = "{\"status\":\"INTERNAL_SERVER_ERROR\",\"estadoOperacion\":\"ERROR\",\"message\":\"Se ha presentado un error al consultar producto.\",\"data\":\"Error: la información suministrada no coincide.\"}"))

            )
    })
    public ResponseEntity<ResponseGenerico> actualizar(@PathVariable Long id,
            @Valid @RequestBody ProductoRequestDto productoRequestDto) {
        ResponseGenerico servicio = this.iProductoService.actualizarProducto(id, productoRequestDto);
        return new ResponseEntity<>(servicio, servicio.getStatus());
    }

    @DeleteMapping("eliminar/{id}")
    @Operation(summary = "Eliminar el producto", description = "Método que permite eliminar el producto en especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "producto eliminado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseGenerico.class),
                            examples = @ExampleObject(name = "example1", value = "{\"status\":\"OK\",\"estadoOperacion\":\"SUCCESS\"," +
                                    "\"message\":\"Se ha actualizado de manera correcta.\"," +
                                    "\"data\":{\"nombre\":\"Empresas olimpica S.A\",\"nit\":\"123456-7363\"," +
                                    "\"propietario\":\"Carlos mario Tovar\",\"direccion\":\"Calle 40 # 31-3A\"," +
                                    "\"telefono\":\"0018002882\",\"email\":\"Olimpica@gmail.com\",\"estadoRegistro\":ACTIVO}}"
                            ))),
            @ApiResponse(responseCode = "404", description = "La producto con el ID especificado no se encuentra.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseGenerico.class),
                            examples = @ExampleObject(name = "example1", value = "{\"status\":\"BAD_REQUEST\",\"estadoOperacion\":\"ERROR\"," +
                                    "\"message\":\"Error: La producto con el ID especificado no se encuentra.\",\"data\":null}"
                            )
                    )

            )
    })
    public ResponseEntity<ResponseGenerico> eliminar(@PathVariable Long id) {
        ResponseGenerico servicio = this.iProductoService.eliminar(id);
        return new ResponseEntity<>(servicio, servicio.getStatus());
    }
    
}

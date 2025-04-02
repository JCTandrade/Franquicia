package com.franquicia.services;

import com.franquicia.dto.ResponseGenerico;
import org.springframework.web.bind.annotation.PathVariable;

public interface IProductoFranquiciaService {

    ResponseGenerico obtenerSucursalesPorFranquicia(@PathVariable Long id);
}

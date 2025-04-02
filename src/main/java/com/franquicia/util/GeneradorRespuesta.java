package com.franquicia.util;

import com.franquicia.Enum.EstadosEnum;
import com.franquicia.constant.Constant;
import com.franquicia.dto.ResponseGenerico;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class GeneradorRespuesta {
    public GeneradorRespuesta() {
    }

    public ResponseGenerico generarRespuesta(HttpStatus status, EstadosEnum estadosEnum, String message, Object data) {
        return new ResponseGenerico(status, estadosEnum, message, data);
    }

    public ResponseGenerico noExiste() {
        return this.generarRespuesta(HttpStatus.OK, EstadosEnum.SUCCESS, Constant.Message.NO_DATA, null);
    }

    public ResponseGenerico noCoincide() {
        return this.generarRespuesta(HttpStatus.BAD_REQUEST, EstadosEnum.ERROR, Constant.Message.ERROR_DATA, null);
    }

}
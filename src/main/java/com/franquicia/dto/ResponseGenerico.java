package com.franquicia.dto;

import com.franquicia.Enum.EstadosEnum;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGenerico {
    private HttpStatus status;
    private EstadosEnum estadoOperacion;
    private String message;
    private Object data;

    public HttpStatus getStatus() {
        return status;
    }

}
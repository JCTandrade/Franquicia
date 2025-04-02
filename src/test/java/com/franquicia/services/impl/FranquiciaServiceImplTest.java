package com.franquicia.services.impl;

import com.franquicia.constant.Constant;
import com.franquicia.dto.FranquiciaRequestDto;
import com.franquicia.dto.ResponseGenerico;
import com.franquicia.entities.FranquiciaEntity;
import com.franquicia.repository.FranquiciaRepository;
import com.franquicia.util.GeneradorRespuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FranquiciaServiceImplTest {

    @InjectMocks
    FranquiciaServiceImpl franquiciaServiceImpl;

    @Mock
    FranquiciaRepository franquiciaRepository;

    @Mock
    GeneradorRespuesta generadorRespuesta;

    FranquiciaEntity franquiciaEntity;
    FranquiciaRequestDto franquiciaRequestDto;
    ResponseGenerico responseGenerico;

    @BeforeEach
    void setUp() {
        franquiciaRequestDto = new FranquiciaRequestDto();
        franquiciaRequestDto.setNombre("Franquicia test");
        franquiciaRequestDto.setDescripcion("Descripcion franquicia test");

        franquiciaEntity = new FranquiciaEntity();
        franquiciaEntity.setIdFranquicia(1L);

        responseGenerico = new ResponseGenerico();
        responseGenerico.setMessage(Constant.Message.FRANQUICIA_GUARDADA);
    }

    @Test
    void testGuardarFranquiciaOK() {
        when(franquiciaRepository.save(any())).thenReturn(franquiciaEntity);
        when(generadorRespuesta.generarRespuesta(any(), any(), any(), any())).thenReturn(responseGenerico);

        var servicio = this.franquiciaServiceImpl.guardar(franquiciaRequestDto);

        assertNotNull(servicio);
        assertEquals(responseGenerico, servicio);
    }

}
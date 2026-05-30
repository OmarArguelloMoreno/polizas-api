package com.pruebatecnica.polizas.service;

import com.pruebatecnica.polizas.entity.*;
import com.pruebatecnica.polizas.exception.BusinessException;
import com.pruebatecnica.polizas.repository.PolizaRepository;
import com.pruebatecnica.polizas.repository.RiesgoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RiesgoServiceTest {

    @Mock
    private RiesgoRepository riesgoRepository;

    @Mock
    private PolizaRepository polizaRepository;

    @InjectMocks
    private RiesgoService riesgoService;

    @Test
    void noDebeAgregarRiesgoAPolizaIndividual() {

        Poliza poliza = new Poliza();
        poliza.setTipo(TipoPoliza.INDIVIDUAL);
        poliza.setRiesgos(new ArrayList<>());

        poliza.getRiesgos().add(new Riesgo());

        when(polizaRepository.findById(1L))
                .thenReturn(Optional.of(poliza));

        assertThrows(
                BusinessException.class,
                () -> riesgoService.agregarRiesgo(1L, new Riesgo())
        );
    }

    @Test
    void debeAgregarRiesgoAPolizaColectiva() {

        Poliza poliza = new Poliza();
        poliza.setId(1L);
        poliza.setTipo(TipoPoliza.COLECTIVA);
        poliza.setRiesgos(new ArrayList<>());

        when(polizaRepository.findById(1L))
                .thenReturn(Optional.of(poliza));

        when(polizaRepository.save(any(Poliza.class)))
                .thenReturn(poliza);

        Riesgo riesgo = new Riesgo();
        riesgo.setDireccion("Carrera 10 #20-30");

        Riesgo resultado =
                riesgoService.agregarRiesgo(1L, riesgo);

        assertNotNull(resultado);
    }

    @Test
    void debeCancelarRiesgo() {

        Riesgo riesgo = new Riesgo();
        riesgo.setId(1L);
        riesgo.setEstado(EstadoRiesgo.ACTIVO);

        when(riesgoRepository.findById(1L))
                .thenReturn(Optional.of(riesgo));

        when(riesgoRepository.save(any(Riesgo.class)))
                .thenReturn(riesgo);

        Riesgo resultado =
                riesgoService.cancelarRiesgo(1L);

        assertEquals(
                EstadoRiesgo.CANCELADO,
                resultado.getEstado()
        );
    }
    
    @Test
    void noDebeAgregarSegundoRiesgoAPolizaIndividual() {

        Poliza poliza = new Poliza();
        poliza.setTipo(TipoPoliza.INDIVIDUAL);
        poliza.setRiesgos(new ArrayList<>());

        poliza.getRiesgos().add(new Riesgo());

        when(polizaRepository.findById(1L))
                .thenReturn(Optional.of(poliza));

        assertThrows(
                BusinessException.class,
                () -> riesgoService.agregarRiesgo(1L, new Riesgo())
        );
    }    
}

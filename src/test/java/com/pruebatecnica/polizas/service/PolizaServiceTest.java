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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PolizaServiceTest {

    @Mock
    private PolizaRepository polizaRepository;

    @Mock
    private RiesgoRepository riesgoRepository;

    @InjectMocks
    private PolizaService polizaService;

    @Test
    void noDebeRenovarPolizaCancelada() {

        Poliza poliza = new Poliza();
        poliza.setId(1L);
        poliza.setEstado(EstadoPoliza.CANCELADA);

        when(polizaRepository.findById(1L))
                .thenReturn(Optional.of(poliza));

        assertThrows(
                BusinessException.class,
                () -> polizaService.renovar(1L)
        );
    }

    @Test
    void debeRenovarPolizaActiva() {

        Poliza poliza = new Poliza();

        poliza.setId(1L);
        poliza.setEstado(EstadoPoliza.ACTIVA);
        poliza.setCanonMensual(new BigDecimal("1000"));
        poliza.setPrima(new BigDecimal("100"));
        poliza.setIpcAplicado(new BigDecimal("0.10"));

        when(polizaRepository.findById(1L))
                .thenReturn(Optional.of(poliza));

        when(polizaRepository.save(any(Poliza.class)))
                .thenReturn(poliza);

        Poliza resultado = polizaService.renovar(1L);

        assertEquals(
                EstadoPoliza.RENOVADA,
                resultado.getEstado()
        );
    }

    @Test
    void cancelarPolizaDebeCancelarSusRiesgos() {

        Poliza poliza = new Poliza();
        poliza.setId(1L);

        Riesgo riesgo = new Riesgo();
        riesgo.setEstado(EstadoRiesgo.ACTIVO);

        when(polizaRepository.findById(1L))
                .thenReturn(Optional.of(poliza));

        when(riesgoRepository.findByPolizaId(1L))
                .thenReturn(List.of(riesgo));

        when(polizaRepository.save(any(Poliza.class)))
                .thenReturn(poliza);

        Poliza resultado = polizaService.cancelar(1L);

        assertEquals(
                EstadoPoliza.CANCELADA,
                resultado.getEstado()
        );

        assertEquals(
                EstadoRiesgo.CANCELADO,
                riesgo.getEstado()
        );
    }
}
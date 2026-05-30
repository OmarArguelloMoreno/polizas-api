package com.pruebatecnica.polizas.service;

import com.pruebatecnica.polizas.entity.EstadoRiesgo;
import com.pruebatecnica.polizas.entity.Poliza;
import com.pruebatecnica.polizas.entity.Riesgo;
import com.pruebatecnica.polizas.entity.TipoPoliza;
import com.pruebatecnica.polizas.exception.BusinessException;
import com.pruebatecnica.polizas.repository.PolizaRepository;
import com.pruebatecnica.polizas.repository.RiesgoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiesgoService {

    private final PolizaRepository polizaRepository;
    private final RiesgoRepository riesgoRepository;

    public Riesgo agregarRiesgo(Long polizaId, Riesgo riesgo) {

        Poliza poliza = polizaRepository.findById(polizaId)
                .orElseThrow(() -> new BusinessException("Póliza no encontrada"));

        // 🔥 REGLA 1: Solo 1 riesgo por póliza individual
        if (poliza.getTipo() == TipoPoliza.INDIVIDUAL
                && !poliza.getRiesgos().isEmpty()) {
            throw new BusinessException("Una póliza individual solo puede tener 1 riesgo");
        }

        // 🔥 REGLA 2: Validar tipo de póliza (ejemplo base)
        if (poliza.getTipo() == null) {
            throw new BusinessException("Tipo de póliza inválido");
        }

        // asociar riesgo a póliza
        poliza.agregarRiesgo(riesgo);

        polizaRepository.save(poliza);

        return riesgo;
    }
    
    public List<Riesgo> obtenerRiesgos(Long polizaId) {

        Poliza poliza = polizaRepository.findById(polizaId)
                .orElseThrow(() -> new BusinessException("Póliza no encontrada"));

        return poliza.getRiesgos();
    }
    
    public Riesgo cancelarRiesgo(Long riesgoId) {

        Riesgo riesgo = riesgoRepository.findById(riesgoId)
                .orElseThrow(() -> new BusinessException("Riesgo no encontrado"));

        riesgo.setEstado(EstadoRiesgo.CANCELADO);

        return riesgoRepository.save(riesgo);
    }    
}
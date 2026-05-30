package com.pruebatecnica.polizas.service;

import com.pruebatecnica.polizas.entity.EstadoPoliza;
import com.pruebatecnica.polizas.entity.EstadoRiesgo;
import com.pruebatecnica.polizas.entity.Poliza;
import com.pruebatecnica.polizas.entity.Riesgo;
import com.pruebatecnica.polizas.entity.TipoPoliza;
import com.pruebatecnica.polizas.exception.BusinessException;
import com.pruebatecnica.polizas.repository.PolizaRepository;
import com.pruebatecnica.polizas.repository.RiesgoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PolizaService {

    private final PolizaRepository polizaRepository;
    private final RiesgoRepository riesgoRepository;

    public List<Poliza> buscarPorTipoYEstado(
            TipoPoliza tipo,
            EstadoPoliza estado) {

        return polizaRepository.findByTipoAndEstado(tipo, estado);
    }

    public Poliza renovar(Long polizaId) {

        Poliza poliza = polizaRepository.findById(polizaId)
                .orElseThrow(() ->
                        new BusinessException("Póliza no encontrada"));

        if (poliza.getEstado() == EstadoPoliza.CANCELADA) {
            throw new BusinessException(
                    "No se puede renovar una póliza cancelada");
        }

        BigDecimal factor =
                BigDecimal.ONE.add(poliza.getIpcAplicado());

        poliza.setCanonMensual(
                poliza.getCanonMensual().multiply(factor));

        poliza.setPrima(
                poliza.getPrima().multiply(factor));

        poliza.setEstado(EstadoPoliza.RENOVADA);

        return polizaRepository.save(poliza);
    }

    public Poliza cancelar(Long polizaId) {

        Poliza poliza = polizaRepository.findById(polizaId)
                .orElseThrow(() ->
                        new BusinessException("Póliza no encontrada"));

        poliza.setEstado(EstadoPoliza.CANCELADA);

        List<Riesgo> riesgos =
                riesgoRepository.findByPolizaId(polizaId);

        riesgos.forEach(r ->
                r.setEstado(EstadoRiesgo.CANCELADO));

        riesgoRepository.saveAll(riesgos);

        return polizaRepository.save(poliza);
    }
    
    public Poliza crear(Poliza poliza) {

        validarPolizaIndividual(poliza);

        inicializarPoliza(poliza);

        asociarRiesgos(poliza);

        return polizaRepository.save(poliza);
    }

    private void validarPolizaIndividual(Poliza poliza) {

        if (poliza.getTipo() != TipoPoliza.INDIVIDUAL) {
            return;
        }

        List<Riesgo> riesgos = poliza.getRiesgos();

        if (riesgos == null || riesgos.isEmpty()) {
            throw new BusinessException(
                    "La póliza individual debe tener un riesgo");
        }

        if (riesgos.size() > 1) {
            throw new BusinessException(
                    "Una póliza individual solo puede tener 1 riesgo");
        }
    }

    private void inicializarPoliza(Poliza poliza) {

        if (poliza.getEstado() == null) {
            poliza.setEstado(EstadoPoliza.ACTIVA);
        }
    }

    private void asociarRiesgos(Poliza poliza) {

        if (poliza.getRiesgos() == null) {
            return;
        }

        for (Riesgo riesgo : poliza.getRiesgos()) {

            riesgo.setPoliza(poliza);

            if (riesgo.getEstado() == null) {
                riesgo.setEstado(EstadoRiesgo.ACTIVO);
            }
        }
    } 

}
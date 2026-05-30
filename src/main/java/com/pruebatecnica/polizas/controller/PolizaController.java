package com.pruebatecnica.polizas.controller;

import com.pruebatecnica.polizas.entity.EstadoPoliza;
import com.pruebatecnica.polizas.entity.Poliza;
import com.pruebatecnica.polizas.entity.Riesgo;
import com.pruebatecnica.polizas.entity.TipoPoliza;
import com.pruebatecnica.polizas.service.PolizaService;
import com.pruebatecnica.polizas.service.RiesgoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polizas")
@RequiredArgsConstructor
public class PolizaController {

    private final PolizaService polizaService;
    private final RiesgoService riesgoService;

    @GetMapping
    public List<Poliza> listar(
            @RequestParam TipoPoliza tipo,
            @RequestParam EstadoPoliza estado) {

        return polizaService.buscarPorTipoYEstado(tipo, estado);
    }

    @GetMapping("/{id}/riesgos")
    public List<Riesgo> obtenerRiesgos(
            @PathVariable Long id) {

        return riesgoService.obtenerRiesgos(id);
    }

    @PostMapping("/{id}/renovar")
    public Poliza renovar(
            @PathVariable Long id) {

        return polizaService.renovar(id);
    }

    @PostMapping("/{id}/cancelar")
    public Poliza cancelar(
            @PathVariable Long id) {

        return polizaService.cancelar(id);
    }

    @PostMapping("/{id}/riesgos")
    public Riesgo agregarRiesgo(
            @PathVariable Long id,
            @RequestBody Riesgo riesgo) {

        return riesgoService.agregarRiesgo(id, riesgo);
    }
    
    @PostMapping
    public Poliza crearPoliza(@RequestBody Poliza poliza) {
        return polizaService.crear(poliza);
    }    
}
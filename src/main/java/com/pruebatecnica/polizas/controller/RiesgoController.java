package com.pruebatecnica.polizas.controller;

import com.pruebatecnica.polizas.entity.Riesgo;
import com.pruebatecnica.polizas.service.RiesgoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/riesgos")
@RequiredArgsConstructor
public class RiesgoController {

    private final RiesgoService riesgoService;

    @PostMapping("/{id}/cancelar")
    public Riesgo cancelar(
            @PathVariable Long id) {

        return riesgoService.cancelarRiesgo(id);
    }
}
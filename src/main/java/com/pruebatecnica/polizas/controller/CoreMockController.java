package com.pruebatecnica.polizas.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/core-mock")
@Slf4j
public class CoreMockController {

    @PostMapping("/evento")
    public String registrarEvento(
            @RequestBody Map<String, Object> body) {

        log.info("Evento enviado al CORE: {}", body);

        return "Evento recibido";
    }
}
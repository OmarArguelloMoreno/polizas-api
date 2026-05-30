package com.pruebatecnica.polizas.dto;

import java.util.List;

public class PolizaDTO {

    private Long id;
    private String nombre;
    private List<RiesgoDTO> riesgos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<RiesgoDTO> getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(List<RiesgoDTO> riesgos) {
        this.riesgos = riesgos;
    }
}
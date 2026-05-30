package com.pruebatecnica.polizas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Poliza extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroPoliza;

    @Enumerated(EnumType.STRING)
    private TipoPoliza tipo;

    @Enumerated(EnumType.STRING)
    private EstadoPoliza estado;

    private BigDecimal canonMensual;

    private BigDecimal prima;

    private BigDecimal ipcAplicado;
        
    @OneToMany(mappedBy = "poliza", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Riesgo> riesgos = new ArrayList<>();    
    
    public void agregarRiesgo(Riesgo riesgo) {
        this.riesgos.add(riesgo);
    }    
    
}
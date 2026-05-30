package com.pruebatecnica.polizas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Riesgo extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;

    private String ciudad;

    private BigDecimal valorAsegurado;

    @Enumerated(EnumType.STRING)
    private EstadoRiesgo estado;

    @ManyToOne
    @JoinColumn(name = "poliza_id")
    @JsonBackReference
    private Poliza poliza;
   
}
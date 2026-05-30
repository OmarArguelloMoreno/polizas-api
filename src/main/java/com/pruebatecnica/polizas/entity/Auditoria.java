package com.pruebatecnica.polizas.entity;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Auditoria {

    private LocalDateTime fechaCreacion;

    private String usuarioCreacion;

    private LocalDateTime fechaModificacion;

    private String usuarioModificacion;

    @PrePersist
    protected void prePersist() {

        fechaCreacion = LocalDateTime.now();

        if (usuarioCreacion == null) {
            usuarioCreacion = "SYSTEM";
        }
    }

    @PreUpdate
    protected void preUpdate() {

        fechaModificacion = LocalDateTime.now();

        if (usuarioModificacion == null) {
            usuarioModificacion = "SYSTEM";
        }
    }
}
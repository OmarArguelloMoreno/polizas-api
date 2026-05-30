package com.pruebatecnica.polizas.repository;

import com.pruebatecnica.polizas.entity.EstadoPoliza;
import com.pruebatecnica.polizas.entity.Poliza;
import com.pruebatecnica.polizas.entity.TipoPoliza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolizaRepository extends JpaRepository<Poliza, Long> {

    List<Poliza> findByTipoAndEstado(
            TipoPoliza tipo,
            EstadoPoliza estado
    );

}
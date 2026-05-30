package com.pruebatecnica.polizas.mapper;

import com.pruebatecnica.polizas.dto.PolizaDTO;
import com.pruebatecnica.polizas.entity.Poliza;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PolizaMapper {

    private final ModelMapper modelMapper;

    public PolizaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PolizaDTO toDTO(Poliza poliza) {
        return modelMapper.map(poliza, PolizaDTO.class);
    }

    public Poliza toEntity(PolizaDTO dto) {
        return modelMapper.map(dto, Poliza.class);
    }
}
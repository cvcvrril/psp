package com.example.ej1practicaseguridad.domain.servicios;

import com.example.ej1practicaseguridad.data.repositorios.EmpleadoRepository;

public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }
}

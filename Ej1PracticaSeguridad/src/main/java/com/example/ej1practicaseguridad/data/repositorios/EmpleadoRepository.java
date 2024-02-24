package com.example.ej1practicaseguridad.data.repositorios;

import com.example.ej1practicaseguridad.data.modelo.EmpleadoEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends ListCrudRepository<EmpleadoEntity, Integer> {
}

package com.example.ej1practicaseguridad.data.repositorios;

import com.example.ej1practicaseguridad.data.modelo.InformeEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformeRepository extends ListCrudRepository<InformeEntity, Integer> {
}

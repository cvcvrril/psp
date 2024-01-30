package org.example.springjavafx.data;

import org.example.springjavafx.data.modelo.Permiso;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PermisoRepository extends
        ListCrudRepository<Permiso, UUID> {

    List<Permiso> findByProgramaId(UUID cosaId);

}

package com.example.seguridad.data.repositorios;

import com.example.seguridad.data.modelo.UserEntity;
import com.example.seguridad.utils.Constantes;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<UserEntity, Long> {

    @EntityGraph(attributePaths = {Constantes.ROLES})
    Optional<UserEntity> findByUsername(String username);

    @EntityGraph(attributePaths = {Constantes.ROLES})
    @Query(Constantes.QUERY_GET_ALL_WITH_PERMISOS)
    List<UserEntity> getAllWithPermisos();

}

package com.example.seguridad.data.repositorios;

import com.example.seguridad.data.modelo.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends ListCrudRepository<UserEntity, Long> {

    @EntityGraph(attributePaths = {"roles"})
    Optional<UserEntity> findByUsername(String username);

    @EntityGraph(attributePaths = {"roles"})
    @Query("select u from UserEntity u")
    List<UserEntity> getAllWithPermisos();

}

package com.example.graphqlserverjavainesmr.data.repositorios;

import com.example.graphqlserverjavainesmr.data.modelo.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends ListCrudRepository<UserEntity, UUID> {

    @EntityGraph(attributePaths = {"roles"})
    Optional<UserEntity> findByUsername(String username);

    @EntityGraph(attributePaths = {"roles"})
    @Query("select u from UserEntity u")
    List<UserEntity> getAllWithPermisos();

}

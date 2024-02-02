package com.example.graphqlserverjavainesmr.data.repositorios;

import com.example.graphqlserverjavainesmr.data.modelo.UserEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends ListCrudRepository<UserEntity, UUID> {
}

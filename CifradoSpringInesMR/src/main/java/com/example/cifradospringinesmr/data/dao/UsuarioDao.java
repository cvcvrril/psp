package com.example.cifradospringinesmr.data.dao;

import com.example.cifradospringinesmr.data.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioDao extends ListCrudRepository<User, UUID>{


}

package com.example.cifradospringinesmr.data;

import com.example.cifradospringinesmr.data.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends ListCrudRepository<User, UUID>{


}

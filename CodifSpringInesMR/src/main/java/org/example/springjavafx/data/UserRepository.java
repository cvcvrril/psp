package org.example.springjavafx.data;

import org.example.springjavafx.data.modelo.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends
        ListCrudRepository<User, UUID> {

    Optional<User> findByName(String name);

}

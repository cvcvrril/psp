package org.example.springjavafx.data;

import org.example.springjavafx.data.modelo.Programa;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProgramaRepository extends
        ListCrudRepository<Programa, UUID> {
    List<Programa> findByUserId(UUID userId);
}

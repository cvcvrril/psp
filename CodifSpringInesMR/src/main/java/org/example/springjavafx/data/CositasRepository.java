package org.example.springjavafx.data;

import org.example.springjavafx.data.modelo.Cosa;
import org.example.springjavafx.data.modelo.Cosita;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CositasRepository extends
        ListCrudRepository<Cosita, UUID> {

    List<Cosita> findByCosaId(UUID cosaId);

}

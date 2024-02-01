package com.example.graphqlserverinesmr.data.repositories

import com.example.graphqlserverinesmr.data.modelo.PersonajeEntity
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonajeRepository : ListCrudRepository<PersonajeEntity, Long>{


}
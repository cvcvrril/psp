package com.example.graphqlserverinesmr.data.repositories

import com.example.graphqlserverinesmr.data.modelo.VideojuegoEntity
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VideojuegoRepository : ListCrudRepository<VideojuegoEntity, Long> {
}
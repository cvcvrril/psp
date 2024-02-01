package com.example.graphqlserverinesmr.domain.modelo

import lombok.Data

@Data
data class Personaje (
        val id: Int,
        val nombre: String,
        val descripcion: String,
)
package com.example.graphqlserverinesmr.data.modelo

import com.example.graphqlserverinesmr.domain.modelo.Personaje
import com.example.graphqlserverinesmr.domain.modelo.Videojuego

fun PersonajeEntity.toPersonaje() : Personaje = Personaje()
fun Personaje.toPersonajeEntity() : PersonajeEntity = PersonajeEntity()
fun VideojuegoEntity.toVideojuego() : Videojuego = Videojuego()
fun Videojuego.toVideojuegoEntity() : VideojuegoEntity = VideojuegoEntity()

package com.example.graphqlserverinesmr.domain.usecases

import com.example.graphqlserverinesmr.data.modelo.PersonajeEntity
import com.example.graphqlserverinesmr.data.repositories.PersonajeRepository

//TODO: preguntar duda acerca de la inyección de dependencias sin usar Hilt pq esto no es móviles -> Como es Spring no se pone nada ?????????

class GetAllPersonajesUseCase(var repository: PersonajeRepository){
    operator fun invoke(): List<PersonajeEntity> = repository.findAll()
}
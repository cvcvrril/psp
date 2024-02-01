package com.example.graphqlserverinesmr.data.modelo

import jakarta.persistence.*
import lombok.*

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@Table(name = "personajes")
@AllArgsConstructor
class PersonajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Int = 0
    val nombre: String = ""
    val descripcion: String = ""

}

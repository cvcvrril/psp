package com.example.graphqlserverjavainesmr.data.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@Table(name = "personajes")
@AllArgsConstructor
public class PersonajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "id_videojuego", nullable = false)
    private VideojuegoEntity videojuego;

}

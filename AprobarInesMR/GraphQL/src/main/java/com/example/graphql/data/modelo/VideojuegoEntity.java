package com.example.graphql.data.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@Table(name = "videojuegos")
@AllArgsConstructor
public class VideojuegoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "titulo", nullable = false)
    private String titulo;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.PERSIST)
    private List<PersonajeEntity> personajes;
}

package com.example.graphql.data.modelo;

import com.example.graphql.utils.Constantes;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@Table(name = Constantes.TABLE_PERSONAJES)
@AllArgsConstructor
public class PersonajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constantes.ID, nullable = false)
    private int id;
    @Column(name = Constantes.NOMBRE, nullable = false)
    private String nombre;
    @Column(name = Constantes.DESCRIPCION, nullable = false)
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = Constantes.ID_VIDEOJUEGO, nullable = false)
    private VideojuegoEntity videojuego;

}

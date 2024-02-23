package com.example.graphql.data.modelo;

import com.example.graphql.utils.Constantes;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@Table(name = Constantes.TABLE_VIDEOJUEGOS)
@AllArgsConstructor
public class VideojuegoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constantes.ID, nullable = false)
    private int id;
    @Column(name = Constantes.TITULO, nullable = false)
    private String titulo;
    @Column(name = Constantes.DESCRIPCION)
    private String descripcion;
    @OneToMany(mappedBy = Constantes.VIDEOJUEGO)
    private List<PersonajeEntity> personajes;
    @OneToOne
    private MapaEntity mapa;
}

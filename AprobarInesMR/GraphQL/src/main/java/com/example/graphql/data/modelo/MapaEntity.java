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
@Table(name = Constantes.TABLE_MAPAS)
@AllArgsConstructor
public class MapaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constantes.ID, nullable = false)
    private int id;
    @Column(name = Constantes.NOMBRE, nullable = false)
    private String nombre;
}

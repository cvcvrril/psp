package com.example.ej1practicaseguridad.data.modelo;

import com.example.ej1practicaseguridad.data.modelo.EmpleadoEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "informes")
public class InformeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "documento")
    private String documento;
    private EmpleadoEntity empleadoEntity;

}

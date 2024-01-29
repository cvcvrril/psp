package org.example.springjavafx.data.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "programas")
public class Cosa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    //INFO: Contraseña del programa (cifrada con la KSA)

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "user_name")
    private String username;

    //INFO: contraseña firmada con la privada del usuario

    @Column(name = "firma", columnDefinition = "varchar(500)")
    private String firma;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
    mappedBy = "cosa")
    private List<Cosita> cositas;
}

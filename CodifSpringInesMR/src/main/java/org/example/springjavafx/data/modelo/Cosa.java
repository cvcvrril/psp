package org.example.springjavafx.data.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "cosas")
public class Cosa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
    mappedBy = "cosa")
    private List<Cosita> cositas;
}

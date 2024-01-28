package org.example.springjavafx.data.modelo;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "permisos")
public class Cosita {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "asym")
    private String asym;

    @ManyToOne
    @JoinColumn(name = "programa_id")
    private Cosa cosa;

}

package org.example.springjavafx.data.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springjavafx.utils.Constantes;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constantes.USUARIOS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = Constantes.ID, nullable = false)
    private UUID id;

    @Column(name = Constantes.NAMEUSER, nullable = false)
    private String name;

    @Column(name = Constantes.PASSWORD, nullable = false)
    private String password;

    @OneToMany(mappedBy = Constantes.USER)
    private List<Programa> programas;
}

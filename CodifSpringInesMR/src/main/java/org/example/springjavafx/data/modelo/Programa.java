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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = Constantes.PROGRAMAS)
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = Constantes.ID, nullable = false)
    private UUID id;

    @Column(name = Constantes.NOMBRE, nullable = false)
    private String nombre;

    //INFO: Contraseña del programa (cifrada con la KSA)

    @Column(name = Constantes.CONTRASENA, nullable = false)
    private String contrasena;

    @Column(name = Constantes.USER_NAME)
    private String username;

    //INFO: contraseña firmada con la privada del usuario

    @Column(name = Constantes.FIRMA, columnDefinition = Constantes.VARCHAR_500)
    private String firma;

    @ManyToOne
    @JoinColumn(name = Constantes.USER_ID)
    private User user;

    @OneToMany(
    mappedBy = Constantes.PROGRAMA)
    private List<Permiso> permisos;
}

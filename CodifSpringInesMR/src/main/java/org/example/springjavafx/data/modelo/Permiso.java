package org.example.springjavafx.data.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.util.UUID;

/**He cambiado lo de visualizadores por permisos porque así lo veía mejor**/

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permisos")
public class Permiso {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "user_name", nullable = false)
    private String username;

    //INFO: contraseña firmada con la privada del usuario, cifrada con la KSA

    @Column(name = "asym")
    private String asym;

    @ManyToOne
    @JoinColumn(name = "programa_id")
    private Programa programa;

}

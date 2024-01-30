package org.example.springjavafx.data.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import org.example.springjavafx.utils.Constantes;

import java.util.UUID;

/**He cambiado lo de visualizadores por permisos porque así lo veía mejor**/

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = Constantes.PERMISOS)
public class Permiso {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = Constantes.ID, nullable = false)
    private UUID id;

    @Column(name = Constantes.USER_NAME, nullable = false)
    private String username;

    //INFO: contraseña firmada con la privada del usuario, cifrada con la KSA

    @Column(name = Constantes.ASYM)
    private String asym;

    @ManyToOne
    @JoinColumn(name = Constantes.PROGRAMA_ID)
    private Programa programa;

}

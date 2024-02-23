package com.example.seguridad.data.modelo;

import com.example.seguridad.utils.Constantes;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@Table(name = Constantes.TABLE_ROLES)
@AllArgsConstructor
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constantes.ID, nullable = false)
    private Long id;

    @Column(name = Constantes.ROL, nullable = false)
    private String rol;


}

package com.example.seguridad.data.modelo;

import com.example.seguridad.utils.Constantes;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = Constantes.TABLE_USERS)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constantes.ID, nullable = false)
    private Long id;
    @Column(name = Constantes.USERNAME, nullable = false)
    private String username;
    @Column(name = Constantes.PASSWORD, nullable = false)
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    private RolEntity roles;

}

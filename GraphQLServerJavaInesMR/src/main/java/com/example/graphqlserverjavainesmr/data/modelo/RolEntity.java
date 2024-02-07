package com.example.graphqlserverjavainesmr.data.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@Table(name = "roles")
@AllArgsConstructor
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rol", nullable = false)
    private String rol;


}

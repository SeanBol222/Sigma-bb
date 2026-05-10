package com.bolivar.bioingenieria.app.sigma_bb.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persona")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID k_identificador;

    @NotNull
    @Column(name = "k_cedula", nullable = false, unique = true)
    private int k_cedula;

    @NotNull
    @Column(name = "n_primer_nombre", nullable = false)
    private String n_primer_nombre;

    @Column(name = "n_segundo_nombre")
    private String n_segundo_nombre;

    @NotNull
    @Column(name = "n_primer_apellido", nullable = false)
    private String n_primer_apellido;

    @NotNull
    @Column(name = "n_segundo_apellido", nullable = false)
    private String n_segundo_apellido;

    @NotNull
    @Column(name = "t_tipo_persona", nullable = false)
    private String t_tipo_persona;

    @Column(name = "t_segundo_tipo_persona")
    private String t_segundo_tipo_persona;
}

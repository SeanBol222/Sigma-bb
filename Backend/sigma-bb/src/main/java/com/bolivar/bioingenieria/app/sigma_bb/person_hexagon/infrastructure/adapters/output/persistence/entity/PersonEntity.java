package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Column(name = "k_cedula", nullable = false, unique = true)
    private String k_cedula;

    @NotBlank
    @Column(name = "n_primer_nombre", nullable = false)
    private String n_primer_nombre;

    @Column(name = "n_segundo_nombre")
    private String n_segundo_nombre;

    @NotBlank
    @Column(name = "n_primer_apellido", nullable = false)
    private String n_primer_apellido;

    @NotBlank
    @Column(name = "n_segundo_apellido", nullable = false)
    private String n_segundo_apellido;

    @NotBlank
    @Column(name = "t_tipo_persona", nullable = false)
    private String t_tipo_persona;

    @Column(name = "t_segundo_tipo_persona")
    private String t_segundo_tipo_persona;
}

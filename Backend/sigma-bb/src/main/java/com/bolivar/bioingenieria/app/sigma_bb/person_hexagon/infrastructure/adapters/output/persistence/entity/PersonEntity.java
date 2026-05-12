package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persona")
public class PersonEntity {

    @Id
    @Column(name = "k_identificador", nullable = false, unique = true)
    private UUID identificador;

    @NotBlank
    @Column(name = "k_cedula", nullable = false, unique = true)
    private String cedula;

    @NotBlank
    @Column(name = "n_primer_nombre", nullable = false)
    private String primerNombre;

    @Column(name = "n_segundo_nombre")
    private String segundoNombre;

    @NotBlank
    @Column(name = "n_primer_apellido", nullable = false)
    private String primerApellido;

    @NotBlank
    @Column(name = "n_segundo_apellido", nullable = false)
    private String segundoApellido;

    @NotBlank
    @Column(name = "t_tipo_persona", nullable = false)
    private String tipoPersona;

    @Column(name = "t_segundo_tipo_persona")
    private String segundoTipoPersona;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhonePersonEntity> phonePersonList;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailPersonEntity> emailPersonList;
}

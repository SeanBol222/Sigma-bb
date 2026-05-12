package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "correo_persona")
public class EmailPersonEntity {

    @Id
    @Column(name = "k_id_correo_persona", nullable = false, unique = true)
    private UUID idCorreoPersona;

    @ManyToOne
    @JoinColumn(name = "k_identificador")
    private PersonEntity person;

    @NotBlank
    @Column(name = "n_correo_persona", nullable = false)
    private String correoPersona;
}

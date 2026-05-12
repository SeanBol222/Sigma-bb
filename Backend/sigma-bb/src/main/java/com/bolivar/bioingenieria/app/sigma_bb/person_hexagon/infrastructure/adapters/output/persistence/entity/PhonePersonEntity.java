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
@Table(name = "telefono_persona")
public class PhonePersonEntity {

    @Id
    @Column(name = "k_id_telefono_persona", nullable = false, unique = true)
    private UUID idTelefonoPersona;

    @ManyToOne
    @JoinColumn(name = "k_identificador")
    private PersonEntity person;

    @NotBlank
    @Column(name = "n_telefono_persona", nullable = false)
    private String telefonoPersona;
}

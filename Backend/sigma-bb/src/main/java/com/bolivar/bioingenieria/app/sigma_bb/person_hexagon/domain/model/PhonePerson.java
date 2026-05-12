package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhonePerson {
    private UUID idTelefonoPersona;
    private String telefonoPersona;
}

package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailPerson {
    private UUID idCorreoPersona;
    private String correoPersona;
}

package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.model.communication.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailPersonCommunicationRequest {

    /**
     * Dirección de correo electrónico de la persona.
     */
    private String correoPersona;
}

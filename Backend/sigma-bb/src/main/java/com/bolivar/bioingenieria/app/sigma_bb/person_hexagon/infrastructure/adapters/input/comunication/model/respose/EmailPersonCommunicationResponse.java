package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.model.respose;

import lombok.*;

/**
 * Respuesta para la comunicación de correo electrónico de una persona.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailPersonCommunicationResponse {

    /**
     * Dirección de correo electrónico de la persona.
     */
    private String correoPersona;
}

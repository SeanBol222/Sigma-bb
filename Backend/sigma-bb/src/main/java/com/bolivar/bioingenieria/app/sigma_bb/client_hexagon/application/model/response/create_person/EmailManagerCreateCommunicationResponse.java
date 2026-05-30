package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.create_person;

import lombok.*;

/**
 * Clase de respuesta para la creación de una comunicación de correo electrónico en el módulo de gestión de personas.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailManagerCreateCommunicationResponse {

    /**
    * Dirección de correo electrónico de la persona.
    */
    private String correoPersona;
}

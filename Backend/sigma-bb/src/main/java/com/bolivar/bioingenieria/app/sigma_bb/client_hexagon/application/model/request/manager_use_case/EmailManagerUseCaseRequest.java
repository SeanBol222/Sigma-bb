package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.manager_use_case;

import lombok.*;

/**
 * Clase que representa la solicitud para gestionar el correo electrónico de una persona.
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailManagerUseCaseRequest {

    /**
     * Dirección de correo electrónico de la persona.
     */
    private String correoPersona;
}

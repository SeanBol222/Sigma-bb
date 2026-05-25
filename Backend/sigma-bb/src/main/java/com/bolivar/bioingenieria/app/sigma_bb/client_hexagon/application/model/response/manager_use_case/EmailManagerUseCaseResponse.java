package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.use_case;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailManagerUseCaseResponse {

    /**
     * Dirección de correo electrónico de la persona.
     */
    private String correoPersona;
}

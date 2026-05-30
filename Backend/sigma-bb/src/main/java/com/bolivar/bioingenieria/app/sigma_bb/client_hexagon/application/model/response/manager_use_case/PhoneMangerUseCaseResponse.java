package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.manager_use_case;

import lombok.*;

/**
 * DTO de entrada para la comunicación relacionada con el teléfono de una persona.
 * Se utiliza en la capa de aplicación para recibir la información del número telefónico
 * asociada a una persona, permitiendo su procesamiento en la lógica de negocio.
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneMangerUseCaseResponse {

    /**
     * Número telefónico de la persona.
     */
    private String telefonoPersona;
}

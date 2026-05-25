package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.create;

import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * Clase de respuesta para la creación de una comunicación en el módulo de gestión de personas.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerCreateCommunicationResponse {

    /**
     * Identificador único de la persona.
     */
    private UUID identificador;

    /**
     * Número de cédula de la persona.
     */
    private String cedula;

    /**
     * Primer nombre de la persona.
     */
    private String primerNombre;

    /**
     * Segundo nombre de la persona.
     */
    private String segundoNombre;

    /**
     * Primer apellido de la persona.
     */
    private String primerApellido;

    /**
     * Segundo apellido de la persona.
     */
    private String segundoApellido;

    /**
     * Lista de teléfonos asociados a la persona.
     */
    private List<PhoneManagerCreateCommunicationResponse> phonePersonList;

    /**
     * Lista de correos electrónicos asociados a la persona.
     */
    private List<EmailManagerCreateCommunicationResponse> emailPersonList;

    /**
     * Tipo principal de persona.
     */
    private String tipoPersona;

}

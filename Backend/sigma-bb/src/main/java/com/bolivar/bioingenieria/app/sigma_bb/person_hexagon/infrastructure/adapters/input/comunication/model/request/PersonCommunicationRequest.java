package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.model.communication.request;

import lombok.*;

import java.util.List;

/**
 * Clase que representa la solicitud de comunicación para una persona.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonCommunicationRequest {

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
    private List<PhonePersonCommunicationRequest> phonePersonList;

    /**
     * Lista de correos electrónicos asociados a la persona.
     */
    private List<EmailPersonCommunicationRequest> emailPersonList;

    /**
     * Tipo principal de persona.
     */
    private String tipoPersona;

}

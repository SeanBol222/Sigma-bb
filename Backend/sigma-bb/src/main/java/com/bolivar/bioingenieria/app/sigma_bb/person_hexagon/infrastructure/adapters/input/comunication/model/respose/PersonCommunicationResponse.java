package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.model.respose;

import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * Respuesta para la comunicación de información de una persona.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonCommunicationResponse {

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
    private List<PhonePersonCommunicationResponse> phonePersonList;

    /**
     * Lista de correos electrónicos asociados a la persona.
     */
    private List<EmailPersonCommunicationResponse> emailPersonList;

    /**
     * Tipo principal de persona.
     */
    private String tipoPersona;

}

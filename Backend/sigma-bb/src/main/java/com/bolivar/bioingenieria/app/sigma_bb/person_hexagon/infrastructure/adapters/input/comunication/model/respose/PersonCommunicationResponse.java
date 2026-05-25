package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.model.communication.respose;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonComunicationResponse {

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
     * Tipo principal de persona.
     */
    private String tipoPersona;

}

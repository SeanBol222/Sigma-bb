package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.manager_use_case;

import lombok.*;

import java.util.List;

/**
 * DTO de entrada para la comunicación relacionada con la información personal de una persona.
 * Se utiliza en la capa de aplicación para recibir la información personal asociada a una persona,
 * permitiendo su procesamiento en la lógica de negocio.
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ManagerUseCaseRequest {

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
    private List<PhoneMangerUseCaseRequest> phonePersonList;

    /**
     * Lista de correos electrónicos asociados a la persona.
     */
    private List<EmailManagerUseCaseRequest> emailPersonList;

    /**
     * Tipo principal de persona.
     */
    private String tipoPersona;

}

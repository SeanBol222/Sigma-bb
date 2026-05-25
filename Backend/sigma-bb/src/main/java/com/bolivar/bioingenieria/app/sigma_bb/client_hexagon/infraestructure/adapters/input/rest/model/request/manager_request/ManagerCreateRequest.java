package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infraestructure.adapters.input.rest.model.request.manager_request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

/**
 * DTO de entrada para crear un nuevo encargado dentro de una sede o Area de servicio de cliente.
 * Contiene la información necesaria para registrar un encargado en el sistema,
 * incluyendo detalles del tipo o rol del encargado en la organización.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ManagerCreateRequest",
        description = "DTO de entrada para crear un nuevo encargado dentro de una sede o Area de servicio de cliente. " +
                "Contiene la información necesaria para registrar un encargado en el sistema, " +
                "incluyendo detalles del tipo o rol del encargado en la organización.")
@ToString
public class ManagerCreateRequest {

    /**
     * Cédula del manager.
     */
    @Schema(description = "Número de cédula de la persona",
            example = "123456789",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "La cédula es obligatoria")
    private String cedula;

    /**
     * Primer nombre del manager.
     */
    @Schema(description = "Primer nombre de la persona",
            example = "Juan",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "El primer nombre es obligatorio")
    private String primerNombre;

    /**
     * Segundo nombre del manager.
     */
    @Schema(description = "Segundo nombre de la persona",
            example = "Carlos",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String segundoNombre;

    /**
     * Primer apellido del manager.
     */
    @Schema(description = "Primer apellido de la persona",
            example = "Pérez",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "El primer apellido es obligatorio")
    private String primerApellido;

    /**
     * Segundo apellido del manager.
     */
    @Schema(description = "Segundo apellido de la persona",
            example = "Gómez",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String segundoApellido;

    /**
     * Lista de teléfonos asociados a la persona.
     */
    @Schema(description = "Lista de teléfonos asociados a la persona",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<PhoneManagerCreateRequest> phonePersonList;

    /**
     * Lista de correos electrónicos asociados a la persona.
     */
    @Schema(description = "Lista de correos electrónicos asociados a la persona",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<EmailManagerCreateRequest> emailPersonList;

    /**
     * Tipo o rol del encargado en la organización.
     * Ejemplos: sede, area_servicio.
     */
    @Schema(description = "Tipo o rol del encargado en el cliente ('HEADQUARTER', 'SERVICE_AREA')",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "HEADQUARTER")
    private String tipoEncargado;


}

package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonUpdateRequest {

    @NotEmpty(message = "La cédula es obligatoria")
    private String cedula;

    @NotEmpty(message = "El primer nombre es obligatorio")
    private String primerNombre;

    private String segundoNombre;

    @NotEmpty(message = "El primer apellido es obligatorio")
    private String primerApellido;

    private String segundoApellido;

    @NotEmpty(message = "El tipo de persona es obligatorio")
    private String tipoPersona;

    private String segundoTipoPersona;

}

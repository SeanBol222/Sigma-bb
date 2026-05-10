package org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonCreateRequest {

    @NotEmpty(message = "La cédula es obligatoria")
    private int k_cedula;

    @NotEmpty(message = "El primer nombre es obligatorio")
    private String n_primer_nombre;

    private String n_segundo_nombre;

    @NotEmpty(message = "El primer apellido es obligatorio")
    private String n_primer_apellido;

    private String n_segundo_apellido;

    @NotEmpty(message = "El tipo de persona es obligatorio")
    private String t_tipo_persona;

    private String t_segundo_tipo_persona;

}

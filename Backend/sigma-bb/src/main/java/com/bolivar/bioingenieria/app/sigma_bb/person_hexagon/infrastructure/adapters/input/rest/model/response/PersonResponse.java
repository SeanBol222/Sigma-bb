package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.response;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {

    private UUID identificador;
    private String cedula;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String tipoPersona;
    private String segundoTipoPersona;
    private List<EmailPersonResponse> emailPersonList;
    private List<PhonePersonResponse> phonePersonList;
}

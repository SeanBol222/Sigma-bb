package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {

    private UUID identificador;
    private String cedula;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String tipoPersona;
    private String segundoTipoPersona;
    private List<EmailPerson> emailPersonList;
    private List<PhonePerson> phonePersonList;


}

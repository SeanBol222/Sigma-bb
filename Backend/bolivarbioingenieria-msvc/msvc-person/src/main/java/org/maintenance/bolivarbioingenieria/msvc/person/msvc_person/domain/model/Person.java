package org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.domain.model;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private UUID k_identificador;
    private int k_cedula;
    private String n_primer_nombre;
    private String n_segundo_nombre;
    private String n_primer_apellido;
    private String n_segundo_apellido;
    private String t_tipo_persona;
    private String t_segundo_tipo_persona;

}

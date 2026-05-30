package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.create_person;

import lombok.*;

/**
 * Clase de respuesta para la creación de una comunicación telefónica en el módulo de gestión de personas.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneManagerCreateCommunicationResponse {

     /**
      * Número de teléfono asociado a la persona.
      */
     private String telefonoPersona;
}

package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.ports.output;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.model.communication.request.PersonCommunicationRequest;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.model.communication.respose.PersonComunicationResponse;

import java.util.UUID;

/**
 * Puerto de salida para la comunicación relacionada con personas.
 * Define las operaciones que pueden ser realizadas para obtener información
 * de personas desde otros módulos o servicios dentro de la aplicación.
 *
 * <p>Este puerto es utilizado por adaptadores que necesitan acceder a datos de personas
 * sin depender directamente de la lógica de negocio o la persistencia.</p>
 */
public interface PersonCommunicationPort {

    /**
     * Busca la información de una persona por su UUID.
     *
     * @param id UUID de la persona a buscar
     * @return PersonComunicationResponse que contiene la información de la persona encontrada
     */
    PersonComunicationResponse findById(UUID id);

    /**
     * Guarda la información de una persona a partir de los datos proporcionados en el request.
     *
     * @param request PersonCommunicationRequest que contiene los datos de la persona a guardar
     * @return UUID de la persona guardada
     */
    UUID save(PersonCommunicationRequest request);
}

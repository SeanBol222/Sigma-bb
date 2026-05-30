package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.controller;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.ports.input.PersonServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.ports.input.PersonCommunicationPort;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.model.request.PersonCommunicationRequest;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.model.respose.PersonCommunicationResponse;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.mapper.PersonCommunicationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Adaptador de comunicación para la entidad Persona.
 * Este adaptador implementa el puerto de comunicación PersonCommunicationPort
 * y se encarga de interactuar con el servicio de persona (PersonServicePort)
 * para obtener y guardar información de personas.
 *
 * <p>Utiliza un mapper (PersonCommunicationMapper) para convertir entre los modelos
 * de comunicación (request/response) y los modelos de dominio utilizados por el servicio.</p>
 */
@Component
@AllArgsConstructor
public class PersonCommunicationAdapter implements PersonCommunicationPort {

    private final PersonServicePort personServicePort;
    private final PersonCommunicationMapper personCommunicationMapper;

    /**
     * Busca la información de una persona por su UUID.
     *
     * @param id UUID de la persona a buscar
     * @return PersonCommunicationResponse que contiene la información de la persona encontrada
     */
    @Override
    public PersonCommunicationResponse findById(UUID id) {
        return personCommunicationMapper.toPersonCommunicationResponse(personServicePort.findById(id));
    }

    /**
     * Guarda la información de una persona a partir de los datos proporcionados en el request.
     *
     * @param request PersonCommunicationRequest que contiene los datos de la persona a guardar
     * @return UUID de la persona guardada
     */
    @Override
    public UUID save(PersonCommunicationRequest request) {
        return personServicePort.save(
                personCommunicationMapper.toPerson(request)
                )
                .getIdentificador();
    }
}

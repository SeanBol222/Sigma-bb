package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.controller;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.ports.input.PersonServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.ports.input.PersonCommunicationPort;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.model.request.PersonCommunicationRequest;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.model.respose.PersonCommunicationResponse;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.mapper.PersonCommunicationMapper;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class PersonComunicationAdapter implements PersonCommunicationPort {

    private final PersonServicePort personServicePort;
    private final PersonCommunicationMapper personCommunicationMapper;

    @Override
    public PersonCommunicationResponse findById(UUID id) {
        return personCommunicationMapper.toPersonCommunicationResponse(personServicePort.findById(id));
    }

    @Override
    public UUID save(PersonCommunicationRequest request) {
        return personCommunicationMapper
                .toPersonCommunicationResponse(
                        personServicePort.save(
                                personCommunicationMapper.toPerson(request)
                        )
                )
                .getIdentificador();
    }
}

package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.service;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.ports.input.PersonServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.ports.output.PersonPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.exception.PersonNotFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService implements PersonServicePort {

    private final PersonPersistencePort personPersistencePort;

    @Override
    public Person findById(UUID id) {
        return personPersistencePort.findById(id)
                .orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public List<Person> findAll() {
        return personPersistencePort.findAll();
    }

    @Override
    public Person save(Person person) {
        return personPersistencePort.save(person);
    }

    @Override
    public Person update(UUID id, Person person) {
        return personPersistencePort.findById(id)
                .map(existingPerson -> {
                    existingPerson.setK_cedula(person.getK_cedula());
                    existingPerson.setN_primer_nombre(person.getN_primer_nombre());
                    existingPerson.setN_segundo_nombre(person.getN_segundo_nombre());
                    existingPerson.setN_primer_apellido(person.getN_primer_apellido());
                    existingPerson.setN_segundo_apellido(person.getN_segundo_apellido());
                    existingPerson.setT_tipo_persona(person.getT_tipo_persona());
                    existingPerson.setT_segundo_tipo_persona(person.getT_segundo_tipo_persona());
                    return personPersistencePort.save(existingPerson);
                })
                .orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public void delete(UUID id) {
        if (!personPersistencePort.findById(id).isPresent()) {
            throw new PersonNotFoundException();
        }
        personPersistencePort.delete(id);
    }
}

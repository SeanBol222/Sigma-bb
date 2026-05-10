package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.ports.input;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.Person;

import java.util.List;
import java.util.UUID;

public interface ClientServicePort {
    Person findById(UUID id);
    List<Person> findAll();
    Person save(Person person);
    Person update(UUID id, Person person);
    void delete(UUID id);
}

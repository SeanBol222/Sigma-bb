package org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.application.ports.input;

import org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.domain.model.Person;

import java.util.List;
import java.util.UUID;

public interface PersonServicePort {

    Person findById(UUID id);
    List<Person> findAll();
    Person save(Person person);
    Person update(UUID id, Person person);
    void delete(UUID id);

}

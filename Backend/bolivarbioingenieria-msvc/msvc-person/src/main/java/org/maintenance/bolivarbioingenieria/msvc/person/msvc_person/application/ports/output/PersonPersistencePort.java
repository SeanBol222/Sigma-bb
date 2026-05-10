package org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.application.ports.output;

import org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.domain.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonPersistencePort {

    Optional<Person> findById(UUID id);
    List<Person> findAll();
    Person save(Person person);
    void delete(UUID id);

}

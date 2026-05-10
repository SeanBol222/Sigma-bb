package com.bolivar.bioingenieria.app.sigma_bb.application.ports.output;

import com.bolivar.bioingenieria.app.sigma_bb.domain.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonPersistencePort {
    Optional<Person> findById(UUID id);
    List<Person> findAll();
    Person save(Person person);
    void delete(UUID id);
}

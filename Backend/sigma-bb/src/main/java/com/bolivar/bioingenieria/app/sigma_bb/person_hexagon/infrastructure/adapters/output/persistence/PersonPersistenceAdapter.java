package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.output.persistence;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.ports.output.PersonPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.Person;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.output.persistence.mapper.PersonPersistenceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.output.persistence.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PersonPersistenceAdapter implements PersonPersistencePort {

    private final PersonRepository personRepository;
    private final PersonPersistenceMapper personPersistenceMapper;

    @Override
    public Optional<Person> findById(UUID id) {
        return personRepository.findById(id)
                .map(personPersistenceMapper::toPerson);
    }

    @Override
    public List<Person> findAll() {
        return personPersistenceMapper.toPersonList(personRepository.findAll());
    }

    @Override
    public Person save(Person person) {
        return personPersistenceMapper.toPerson(
                personRepository.save(personPersistenceMapper.toPersonEntity(person)));
    }

    @Override
    public void delete(UUID id) {
        personRepository.deleteById(id);
    }

}

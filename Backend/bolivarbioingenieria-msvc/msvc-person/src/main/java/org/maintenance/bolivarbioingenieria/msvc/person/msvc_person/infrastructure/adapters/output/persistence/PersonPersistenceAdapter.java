package org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.infrastructure.adapters.output.persistence;

import lombok.RequiredArgsConstructor;
import org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.application.ports.output.PersonPersistencePort;
import org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.domain.model.Person;
import org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.infrastructure.adapters.output.persistence.mapper.PersonPersistenceMapper;
import org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.infrastructure.adapters.output.persistence.repository.PersonRepository;
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

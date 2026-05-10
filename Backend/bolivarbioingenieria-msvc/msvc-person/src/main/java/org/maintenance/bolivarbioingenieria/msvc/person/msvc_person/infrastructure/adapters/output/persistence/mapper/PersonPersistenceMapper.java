package org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.infrastructure.adapters.output.persistence.mapper;

import org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.domain.model.Person;
import org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.infrastructure.adapters.output.persistence.entity.PersonEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonPersistenceMapper {

    PersonEntity toPersonEntity(Person person);
    Person toPerson(PersonEntity personEntity);

    List<Person> toPersonList(List<PersonEntity> personEntities);
}

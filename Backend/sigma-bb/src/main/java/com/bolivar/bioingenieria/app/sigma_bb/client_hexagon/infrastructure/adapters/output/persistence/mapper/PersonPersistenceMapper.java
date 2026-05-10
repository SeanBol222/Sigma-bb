package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.output.persistence.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.Person;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.output.persistence.entity.PersonEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonPersistenceMapper {

    PersonEntity toPersonEntity(Person person);
    Person toPerson(PersonEntity personEntity);

    List<Person> toPersonList(List<PersonEntity> personEntities);
}

package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.output.persistence.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.Person;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.output.persistence.entity.PersonEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        EmailPersonPersistenceMapper.class,
        PhonePersonPersistenceMapper.class})
public interface PersonPersistenceMapper {

    PersonEntity toPersonEntity(Person person);

    Person toPerson(PersonEntity personEntity);

    List<Person> toPersonList(List<PersonEntity> personEntities);

    @AfterMapping
    default void linkChildren(@MappingTarget PersonEntity personEntity) {
        if (personEntity.getEmailPersonList() != null) {
            personEntity.getEmailPersonList()
                    .forEach(emailPersonEntity ->
                            emailPersonEntity.setPerson(personEntity));
        }
        if (personEntity.getPhonePersonList() != null) {
            personEntity.getPhonePersonList()
                    .forEach(phonePerson ->
                            phonePerson.setPerson(personEntity));
        }
    }
}

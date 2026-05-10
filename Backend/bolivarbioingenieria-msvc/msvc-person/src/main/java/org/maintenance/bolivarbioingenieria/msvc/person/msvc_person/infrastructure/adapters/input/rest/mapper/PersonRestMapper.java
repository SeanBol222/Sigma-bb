package org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.infrastructure.adapters.input.rest.mapper;

import org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.domain.model.Person;
import org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.infrastructure.adapters.input.rest.model.request.PersonCreateRequest;
import org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.infrastructure.adapters.input.rest.model.response.PersonResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface PersonRestMapper {

    Person toPerson(PersonCreateRequest personCreateRequest);

    PersonResponse toPersonResponse(Person person);

    List<PersonResponse> toPersonResponseList(List<Person> personList);

}

package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.input.rest.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.Person;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.request.PersonCreateRequest;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.response.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ClientRestMapper {

    Person toPerson(PersonCreateRequest request);

    PersonResponse toPersonResponse(Person person);

    List<PersonResponse> toPersonResponseList(List<Person> personList);
}

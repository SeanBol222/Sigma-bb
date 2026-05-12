package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.EmailPerson;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.request.EmailPersonCreateRequest;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.response.EmailPersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmailPersonRestMapper {

    EmailPerson toEmailPerson(EmailPersonCreateRequest request);

    EmailPersonResponse toEmailPersonResponse(EmailPerson emailPerson);

    List<EmailPersonResponse> toEmailPersonResponseList(List<EmailPerson> emailPersonList);
}

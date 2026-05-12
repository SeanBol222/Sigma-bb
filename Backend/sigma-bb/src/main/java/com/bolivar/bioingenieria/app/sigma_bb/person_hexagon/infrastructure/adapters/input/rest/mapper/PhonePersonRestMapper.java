package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.PhonePerson;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.request.PhonePersonCreateRequest;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.response.PhonePersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PhonePersonRestMapper {

    PhonePerson toPhonePerson(PhonePersonCreateRequest request);

    PhonePersonResponse toPhonePersonResponse(PhonePerson phonePerson);

    List<PhonePersonResponse> toPhonePersonResponseList(List<PhonePerson> phonePersonList);
}

package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.output.persistence.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.PhonePerson;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.output.persistence.entity.PhonePersonEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhonePersonPersistenceMapper {

    PhonePersonEntity toPhonePersonEntity(PhonePerson phonePerson);

    PhonePerson toPhonePerson(PhonePersonEntity phonePersonEntity);

    List<PhonePerson> toPhonePersonList(List<PhonePersonEntity> phonePersonEntities);
}

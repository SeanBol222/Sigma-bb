package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.output.persistence.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.EmailPerson;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.output.persistence.entity.EmailPersonEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmailPersonPersistenceMapper {

    EmailPersonEntity toEmailPersonEntity(EmailPerson emailPerson);

    EmailPerson toEmailPerson(EmailPersonEntity emailPersonEntity);

    List<EmailPerson> toEmailPersonList(List<EmailPersonEntity> emailPersonEntities);
}

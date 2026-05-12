package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.TechnicalVerification;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities.TechnicalVerificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TechnicalVerificationPersistenceMapper {

    TechnicalVerification toTechnicalVerification(TechnicalVerificationEntity technicalVerificationEntity);

    TechnicalVerificationEntity toTechnicalVerificationEntity(TechnicalVerification technicalVerification);

    List<TechnicalVerification> toTechnicalVerificationList(List<TechnicalVerificationEntity> technicalVerificationEntities);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDomain(TechnicalVerification source, @MappingTarget TechnicalVerificationEntity target);
}

package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.EquipmentType;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities.EquipmentTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EquipmentTypePersistenceMapper {

    EquipmentType toEquipmentType(EquipmentTypeEntity equipmentTypeEntity);

    EquipmentTypeEntity toEquipmentTypeEntity(EquipmentType equipmentType);

    List<EquipmentType> toEquipmentTypeList(List<EquipmentTypeEntity> equipmentTypeEntities);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDomain(EquipmentType source, @MappingTarget EquipmentTypeEntity target);
}

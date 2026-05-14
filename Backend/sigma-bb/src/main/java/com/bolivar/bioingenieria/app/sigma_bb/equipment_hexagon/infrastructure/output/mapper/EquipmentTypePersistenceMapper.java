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
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {MetrologicalDataPersistenceMapper.class}
)
public interface EquipmentTypePersistenceMapper {

    @Mapping(target = "metrologicalData", source = "metrologicalDataEntities")
    EquipmentType toEquipmentType(EquipmentTypeEntity equipmentTypeEntity);

    @Mapping(target = "metrologicalDataEntities", source = "metrologicalData")
    EquipmentTypeEntity toEquipmentTypeEntity(EquipmentType equipmentType);

    List<EquipmentType> toEquipmentTypeList(List<EquipmentTypeEntity> equipmentTypeEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "metrologicalDataEntities", source = "metrologicalData")
    @Mapping(target = "active", ignore = true)
    void updateEntityFromDomain(EquipmentType source, @MappingTarget EquipmentTypeEntity target);
}

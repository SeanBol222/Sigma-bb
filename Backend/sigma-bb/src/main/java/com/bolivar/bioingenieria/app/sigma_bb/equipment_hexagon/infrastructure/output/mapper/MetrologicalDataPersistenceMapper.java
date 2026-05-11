package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.MetrologicalData;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities.MetrologicalDataEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface MetrologicalDataPersistenceMapper {

    @Mapping(target = "id.value", source = "value")
    @Mapping(target = "id.type", source = "type")
    @Mapping(target = "id.equipmentTypeId", source = "equipmentTypeId")
    MetrologicalDataEntity toMetrologicalDataEntity(MetrologicalData metrologicalData);

    @Mapping(target = "value", source = "id.value")
    @Mapping(target = "type", source = "id.type")
    @Mapping(target = "equipmentTypeId", source = "id.equipmentTypeId")
    MetrologicalData toMetrologicalData(MetrologicalDataEntity metrologicalDataEntity);

    List<MetrologicalData> toMetrologicalDataList(List<MetrologicalDataEntity> metrologicalDataEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "id.value", source = "value")
    @Mapping(target = "id.type", source = "type")
    @Mapping(target = "id.equipmentTypeId", source = "equipmentTypeId")
    void updateEntityFromDomain(MetrologicalData source, @MappingTarget MetrologicalDataEntity target);
}

package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.EquipmentType;

import java.util.List;

public interface EquipmentTypeServicePort {
    List<EquipmentType> findAll();
    EquipmentType findById(String id);
    EquipmentType save(EquipmentType equipmentType);
    EquipmentType update(String id, EquipmentType equipmentType);
    void delete(String id);
}

package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Equipment;

import java.util.List;

public interface EquipmentPersistencePort {
    List<Equipment> findAll();
    Equipment findById(String id);
    Equipment save(Equipment equipment);
    Equipment update(String id, Equipment equipment);
    void delete(String id);
}

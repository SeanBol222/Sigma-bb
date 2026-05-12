package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Equipment;

import java.util.List;

public interface EquipmentServicePort {
    List<Equipment> findAll();
    Equipment findById(String id);
    Equipment save(Equipment equipment);
    Equipment update(String id, Equipment equipment);
    void delete(String id);
}

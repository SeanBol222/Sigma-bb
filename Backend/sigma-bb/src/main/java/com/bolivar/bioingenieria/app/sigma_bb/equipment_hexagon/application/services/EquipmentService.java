package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.EquipmentServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output.EquipmentPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService implements EquipmentServicePort {
    private final EquipmentPersistencePort equipmentPersistencePort;

    @Autowired
    public EquipmentService(EquipmentPersistencePort equipmentPersistencePort) {
        this.equipmentPersistencePort = equipmentPersistencePort;
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentPersistencePort.findAll();
    }

    @Override
    public Equipment findById(String id) {
        return this.equipmentPersistencePort.findById(id);
    }

    @Override
    public Equipment save(Equipment equipment) {
        return equipmentPersistencePort.save(equipment);
    }

    @Override
    public Equipment update(String id, Equipment equipment) {
        return equipmentPersistencePort.update(id, equipment);
    }

    @Override
    public void delete(String id) {
        equipmentPersistencePort.delete(id);
    }
}

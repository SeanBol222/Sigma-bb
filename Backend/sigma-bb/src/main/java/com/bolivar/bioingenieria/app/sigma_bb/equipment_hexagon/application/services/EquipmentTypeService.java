package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.EquipmentTypeServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output.EquipmentTypePersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.EquipmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentTypeService implements EquipmentTypeServicePort {
    private final EquipmentTypePersistencePort equipmentTypePersistencePort;

    @Autowired
    public EquipmentTypeService(EquipmentTypePersistencePort equipmentTypePersistencePort) {
        this.equipmentTypePersistencePort = equipmentTypePersistencePort;
    }

    @Override
    public List<EquipmentType> findAll() {
        return equipmentTypePersistencePort.findAll();
    }

    @Override
    public EquipmentType findById(String id) {
        return this.equipmentTypePersistencePort.findById(id);
    }

    @Override
    public EquipmentType save(EquipmentType equipmentType) {
        return equipmentTypePersistencePort.save(equipmentType);
    }

    @Override
    public EquipmentType update(String id, EquipmentType equipmentType) {
        return equipmentTypePersistencePort.update(id, equipmentType);
    }

    @Override
    public void delete(String id) {
        equipmentTypePersistencePort.delete(id);
    }
}

package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output.EquipmentPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Equipment;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities.EquipmentEntity;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.errors.EquipmentNotFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.mapper.BrandPersistenceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.mapper.EquipmentPersistenceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.mapper.EquipmentTypePersistenceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.repository.SpringEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EquipmentPersistenceAdapter implements EquipmentPersistencePort {
    private final SpringEquipmentRepository springEquipmentRepository;
    private final EquipmentPersistenceMapper equipmentPersistenceMapper;

    @Autowired
    public EquipmentPersistenceAdapter(SpringEquipmentRepository springEquipmentRepository,
                                       EquipmentPersistenceMapper equipmentPersistenceMapper) {
        this.springEquipmentRepository = springEquipmentRepository;
        this.equipmentPersistenceMapper = equipmentPersistenceMapper;
    }

    @Override
    public List<Equipment> findAll() {
        List<EquipmentEntity> entities = springEquipmentRepository.findAll();
        return entities.stream()
                .map(this::mapToDomain).toList();
    }

    @Override
    public Equipment findById(String id) {
        UUID uuid = UUID.fromString(id);
        EquipmentEntity entity = springEquipmentRepository.findById(uuid)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
        return mapToDomain(entity);
    }

    @Override
    public Equipment save(Equipment equipment) {
        EquipmentEntity entity = equipmentPersistenceMapper.toEquipmentEntity(equipment);
        EquipmentEntity saved = springEquipmentRepository.save(entity);
        EquipmentEntity reloaded = springEquipmentRepository.findById(saved.getId()).orElse(saved);
        return mapToDomain(reloaded);
    }

    @Override
    public Equipment update(String id, Equipment equipment) {
        UUID uuid = UUID.fromString(id);
        EquipmentEntity existing = springEquipmentRepository.findById(uuid)
                .orElseThrow(() -> new EquipmentNotFoundException(id));

        equipmentPersistenceMapper.updateEntityFromDomain(equipment, existing);

        EquipmentEntity saved = springEquipmentRepository.save(existing);
        EquipmentEntity reloaded = springEquipmentRepository.findById(saved.getId()).orElse(saved);

        return mapToDomain(reloaded);
    }

    @Override
    public void delete(String id) {
        UUID uuid = UUID.fromString(id);
        if (!springEquipmentRepository.existsById(uuid)) {
            throw new EquipmentNotFoundException(id);
        }
        springEquipmentRepository.deleteById(uuid);
    }

    private Equipment mapToDomain(EquipmentEntity entity) {
        return equipmentPersistenceMapper.toEquipment(entity);
    }
}

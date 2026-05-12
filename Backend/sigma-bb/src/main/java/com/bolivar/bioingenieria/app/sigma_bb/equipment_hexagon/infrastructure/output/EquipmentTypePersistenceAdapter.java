package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output.EquipmentTypePersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.EquipmentType;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities.EquipmentTypeEntity;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.errors.EquipmentTypeNotFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.mapper.EquipmentTypePersistenceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.repository.SpringEquipmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EquipmentTypePersistenceAdapter implements EquipmentTypePersistencePort {
    private final SpringEquipmentTypeRepository springEquipmentTypeRepository;
    private final EquipmentTypePersistenceMapper equipmentTypePersistenceMapper;

    @Autowired
    public EquipmentTypePersistenceAdapter(SpringEquipmentTypeRepository springEquipmentTypeRepository,
                                           EquipmentTypePersistenceMapper equipmentTypePersistenceMapper) {
        this.springEquipmentTypeRepository = springEquipmentTypeRepository;
        this.equipmentTypePersistenceMapper = equipmentTypePersistenceMapper;
    }

    @Override
    public List<EquipmentType> findAll() {
        List<EquipmentTypeEntity> equipmentTypeEntities = springEquipmentTypeRepository.findAll();
        return equipmentTypeEntities.stream()
                .map(equipmentTypePersistenceMapper::toEquipmentType).toList();
    }

    @Override
    public EquipmentType findById(String id) {
        UUID uuid = UUID.fromString(id);
        EquipmentTypeEntity equipmentTypeEntity =
                springEquipmentTypeRepository.findById(uuid).orElseThrow(() -> new EquipmentTypeNotFoundException(id));
        return equipmentTypePersistenceMapper.toEquipmentType(equipmentTypeEntity);
    }

    @Override
    public EquipmentType save(EquipmentType equipmentType) {
        EquipmentTypeEntity equipmentTypeEntity = equipmentTypePersistenceMapper.toEquipmentTypeEntity(equipmentType);
        EquipmentTypeEntity equipmentTypeEntitySaved = springEquipmentTypeRepository.save(equipmentTypeEntity);
        return equipmentTypePersistenceMapper.toEquipmentType(equipmentTypeEntitySaved);
    }

    @Override
    public EquipmentType update(String id, EquipmentType equipmentType) {
        UUID uuid = UUID.fromString(id);
        EquipmentTypeEntity existing = springEquipmentTypeRepository.findById(uuid)
                .orElseThrow(() -> new EquipmentTypeNotFoundException(id));

        equipmentTypePersistenceMapper.updateEntityFromDomain(equipmentType, existing);

        EquipmentTypeEntity saved = springEquipmentTypeRepository.save(existing);

        return equipmentTypePersistenceMapper.toEquipmentType(saved);
    }

    @Override
    public void delete(String id) {
        UUID uuid = UUID.fromString(id);
        if (!springEquipmentTypeRepository.existsById(uuid)) {
            throw new EquipmentTypeNotFoundException(id);
        }
        springEquipmentTypeRepository.deleteById(uuid);
    }
}

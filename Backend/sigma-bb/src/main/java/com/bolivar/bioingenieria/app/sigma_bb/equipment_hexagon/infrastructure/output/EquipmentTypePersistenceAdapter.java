package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output.EquipmentTypePersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.EquipmentType;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities.EquipmentTypeEntity;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities.MetrologicalDataEntity;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities.MetrologicalDataId;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.errors.EquipmentTypeNotFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.mapper.EquipmentTypePersistenceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.mapper.MetrologicalDataPersistenceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.repository.SpringEquipmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class EquipmentTypePersistenceAdapter implements EquipmentTypePersistencePort {
    private final SpringEquipmentTypeRepository repository;
    private final EquipmentTypePersistenceMapper mapper;
    private final MetrologicalDataPersistenceMapper mdMapper;

    @Autowired
    public EquipmentTypePersistenceAdapter(SpringEquipmentTypeRepository repository,
                                           EquipmentTypePersistenceMapper mapper,
                                           MetrologicalDataPersistenceMapper mdMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.mdMapper = mdMapper;
    }

    @Override
    @Transactional
    public List<EquipmentType> findAll() {
        return repository.findAll().stream().map(mapper::toEquipmentType).toList();
    }

    @Override
    @Transactional
    public Optional<EquipmentType> findById(String id) {
        return repository.findById(UUID.fromString(id)).map(mapper::toEquipmentType);
    }

    @Override
    @Transactional
    public EquipmentType save(EquipmentType equipmentType) {
        EquipmentTypeEntity entity = mapper.toEquipmentTypeEntity(equipmentType);
        linkChildren(entity);
        return mapper.toEquipmentType(repository.save(entity));
    }

    @Override
    @Transactional
    public EquipmentType update(String id, EquipmentType equipmentType) {
        UUID uuid = UUID.fromString(id);
        EquipmentTypeEntity existing = repository.findById(uuid)
                .orElseThrow(() -> new EquipmentTypeNotFoundException(id));

        softDeleteChildren(existing);
        existing.getMetrologicalDataEntities().clear();

        mapper.updateEntityFromDomain(equipmentType, existing);
        linkChildren(existing);

        return mapper.toEquipmentType(repository.save(existing));
    }

    @Override
    @Transactional
    public void delete(String id) {
        UUID uuid = UUID.fromString(id);
        EquipmentTypeEntity entity = repository.findById(uuid)
                .orElseThrow(() -> new EquipmentTypeNotFoundException(id));

        softDeleteChildren(entity);
        entity.setActive(false);

        repository.save(entity);
    }

    private void linkChildren(EquipmentTypeEntity parent) {
        if (parent.getMetrologicalDataEntities() != null) {
            for (MetrologicalDataEntity child : parent.getMetrologicalDataEntities()) {
                if (child.getId() == null) {
                    child.setId(new MetrologicalDataId());
                }
                child.getId().setEquipmentTypeId(parent.getId());
                child.setEquipmentType(parent);
            }
        }
    }

    private void softDeleteChildren(EquipmentTypeEntity parent) {
        if (parent.getMetrologicalDataEntities() != null) {
            for (MetrologicalDataEntity child : parent.getMetrologicalDataEntities()) {
                child.setActive(false);
            }
        }
    }
}

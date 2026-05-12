package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output.MetrologicalDataPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.MetrologicalData;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities.MetrologicalDataEntity;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities.MetrologicalDataId;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.errors.MetrologicalDataNotFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.mapper.MetrologicalDataPersistenceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.repository.SpringMetrologicalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class MetrologicalDataPersistenceAdapter implements MetrologicalDataPersistencePort {
    private final SpringMetrologicalDataRepository springMetrologicalDataRepository;
    private final MetrologicalDataPersistenceMapper metrologicalDataPersistenceMapper;

    @Autowired
    public MetrologicalDataPersistenceAdapter(SpringMetrologicalDataRepository springMetrologicalDataRepository,
                                              MetrologicalDataPersistenceMapper metrologicalDataPersistenceMapper) {
        this.springMetrologicalDataRepository = springMetrologicalDataRepository;
        this.metrologicalDataPersistenceMapper = metrologicalDataPersistenceMapper;
    }

    @Override
    public List<MetrologicalData> findAll() {
        List<MetrologicalDataEntity> entities = springMetrologicalDataRepository.findAll();
        return entities.stream()
                .map(metrologicalDataPersistenceMapper::toMetrologicalData).toList();
    }

    @Override
    public MetrologicalData findById(String id) {
        MetrologicalDataId metrologicalDataId = parseId(id);
        MetrologicalDataEntity entity = springMetrologicalDataRepository.findById(metrologicalDataId)
                .orElseThrow(() -> new MetrologicalDataNotFoundException(id));
        return metrologicalDataPersistenceMapper.toMetrologicalData(entity);
    }

    @Override
    public MetrologicalData save(MetrologicalData metrologicalData) {
        MetrologicalDataEntity entity = metrologicalDataPersistenceMapper.toMetrologicalDataEntity(metrologicalData);
        MetrologicalDataEntity saved = springMetrologicalDataRepository.save(entity);
        return metrologicalDataPersistenceMapper.toMetrologicalData(saved);
    }

    @Override
    public MetrologicalData update(String id, MetrologicalData metrologicalData) {
        MetrologicalDataId existingId = parseId(id);
        springMetrologicalDataRepository.deleteById(existingId);

        MetrologicalDataEntity entity = metrologicalDataPersistenceMapper.toMetrologicalDataEntity(metrologicalData);
        MetrologicalDataEntity saved = springMetrologicalDataRepository.save(entity);
        return metrologicalDataPersistenceMapper.toMetrologicalData(saved);
    }

    @Override
    public void delete(String id) {
        MetrologicalDataId metrologicalDataId = parseId(id);
        MetrologicalDataEntity entity = springMetrologicalDataRepository.findById(metrologicalDataId)
                .orElseThrow(() -> new MetrologicalDataNotFoundException(id));
        entity.setDeleted(true);
        springMetrologicalDataRepository.save(entity);
    }

    private MetrologicalDataId parseId(String id) {
        String[] parts = id.split(":::", 3);
        BigDecimal value = new BigDecimal(parts[0]);
        String type = parts[1];
        UUID equipmentTypeId = UUID.fromString(parts[2]);
        return new MetrologicalDataId(value, type, equipmentTypeId);
    }
}

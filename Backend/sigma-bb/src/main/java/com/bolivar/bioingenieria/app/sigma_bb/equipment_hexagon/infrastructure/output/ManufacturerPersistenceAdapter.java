package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output.ManufacturerPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Manufacturer;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities.ManufacturerEntity;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.errors.ManufacturerNotFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.mapper.ManufacturerPersistenceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.repository.SpringManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ManufacturerPersistenceAdapter implements ManufacturerPersistencePort {
    private final SpringManufacturerRepository springManufacturerRepository;
    private final ManufacturerPersistenceMapper manufacturerPersistenceMapper;

    @Autowired
    public ManufacturerPersistenceAdapter(SpringManufacturerRepository springManufacturerRepository,
                                           ManufacturerPersistenceMapper manufacturerPersistenceMapper) {
        this.springManufacturerRepository = springManufacturerRepository;
        this.manufacturerPersistenceMapper = manufacturerPersistenceMapper;
    }

    @Override
    public List<Manufacturer> findAll() {
        List<ManufacturerEntity> manufacturersEntities = springManufacturerRepository.findAll();
        return manufacturersEntities.stream()
                .map(manufacturerPersistenceMapper::toManufacturer).toList();
    }

    @Override
    public Optional<Manufacturer> findById(String id) {
        return springManufacturerRepository.findById(id)
                .map(manufacturerPersistenceMapper::toManufacturer);
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        ManufacturerEntity manufacturerEntity = manufacturerPersistenceMapper.toManufacturerEntity(manufacturer);
        ManufacturerEntity manufacturerEntitySaved = springManufacturerRepository.save(manufacturerEntity);
        return manufacturerPersistenceMapper.toManufacturer(manufacturerEntitySaved);
    }

    @Override
    public Manufacturer update(String id, Manufacturer manufacturer) {
        ManufacturerEntity existing = springManufacturerRepository.findById(id)
                .orElseThrow(() -> new ManufacturerNotFoundException(id));

        manufacturerPersistenceMapper.updateEntityFromDomain(manufacturer, existing);

        ManufacturerEntity saved = springManufacturerRepository.save(existing);

        return manufacturerPersistenceMapper.toManufacturer(saved);
    }

    @Override
    public void delete(String id) {
        if (!springManufacturerRepository.existsById(id)) {
            throw new ManufacturerNotFoundException(id);
        }
        springManufacturerRepository.deleteById(id);
    }
}

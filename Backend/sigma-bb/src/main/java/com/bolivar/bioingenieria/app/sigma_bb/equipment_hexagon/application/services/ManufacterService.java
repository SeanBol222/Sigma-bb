package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.ManufacturerServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output.ManufacturerPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacterService implements ManufacturerServicePort {
    private final ManufacturerPersistencePort manufacterPersistencePort;

    @Autowired
    public ManufacterService(ManufacturerPersistencePort manufacterPersistencePort) {
        this.manufacterPersistencePort = manufacterPersistencePort;
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacterPersistencePort.findAll();
    }

    @Override
    public Manufacturer findById(String id) {
        return this.manufacterPersistencePort.findById(id);
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacterPersistencePort.save(manufacturer);
    }

    @Override
    public Manufacturer update(String id, Manufacturer manufacturer) {
        return manufacterPersistencePort.update(id, manufacturer);
    }

    @Override
    public void delete(String id) {
        manufacterPersistencePort.delete(id);
    }
}

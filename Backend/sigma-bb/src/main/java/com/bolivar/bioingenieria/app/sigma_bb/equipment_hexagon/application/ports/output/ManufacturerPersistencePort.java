package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Manufacturer;

import java.util.List;

public interface ManufacturerPersistencePort {
    List<Manufacturer> findAll();
    Manufacturer findById(String id);
    Manufacturer save(Manufacturer manufacturer);
    Manufacturer update(String id, Manufacturer manufacturer);
    void delete(String id);
}

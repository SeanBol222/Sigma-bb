package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerServicePort {
    List<Manufacturer> findAll();
    Manufacturer findById(String id);
    Manufacturer save(Manufacturer manufacturer);
    Manufacturer update(String id, Manufacturer manufacturer);
    void delete(String id);
}

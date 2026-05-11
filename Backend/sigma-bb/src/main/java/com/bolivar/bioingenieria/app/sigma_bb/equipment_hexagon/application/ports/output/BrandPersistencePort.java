package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Brand;

import java.util.List;

public interface BrandPersistencePort {
    List<Brand> findAll();
    Brand findById(String id);
    Brand save(Brand brand);
    Brand update(String id, Brand brand);
    void delete(String id);
}

package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.BrandServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output.BrandPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService implements BrandServicePort {
    private final BrandPersistencePort brandPersistencePort;

    @Autowired
    public BrandService(BrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public List<Brand> findAll() {
        return brandPersistencePort.findAll();
    }

    @Override
    public Brand findById(String id) {
        return this.brandPersistencePort.findById(id);
    }

    @Override
    public Brand save(Brand brand) {
        return brandPersistencePort.save(brand);
    }

    @Override
    public Brand update(String id, Brand brand) {
        return brandPersistencePort.update(id, brand);
    }

    @Override
    public void delete(String id) {
        brandPersistencePort.delete(id);
    }
}

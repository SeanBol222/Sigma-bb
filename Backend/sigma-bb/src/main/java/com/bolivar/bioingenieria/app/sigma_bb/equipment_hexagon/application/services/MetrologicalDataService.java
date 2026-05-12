package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.MetrologicalDataServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output.MetrologicalDataPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.MetrologicalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetrologicalDataService implements MetrologicalDataServicePort {
    private final MetrologicalDataPersistencePort metrologicalDataPersistencePort;

    @Autowired
    public MetrologicalDataService(MetrologicalDataPersistencePort metrologicalDataPersistencePort) {
        this.metrologicalDataPersistencePort = metrologicalDataPersistencePort;
    }

    @Override
    public List<MetrologicalData> findAll() {
        return metrologicalDataPersistencePort.findAll();
    }

    @Override
    public MetrologicalData findById(String id) {
        return this.metrologicalDataPersistencePort.findById(id);
    }

    @Override
    public MetrologicalData save(MetrologicalData metrologicalData) {
        return metrologicalDataPersistencePort.save(metrologicalData);
    }

    @Override
    public MetrologicalData update(String id, MetrologicalData metrologicalData) {
        return metrologicalDataPersistencePort.update(id, metrologicalData);
    }

    @Override
    public void delete(String id) {
        metrologicalDataPersistencePort.delete(id);
    }
}

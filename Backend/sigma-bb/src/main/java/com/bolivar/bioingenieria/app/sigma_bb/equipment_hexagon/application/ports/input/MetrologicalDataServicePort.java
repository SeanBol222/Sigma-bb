package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.MetrologicalData;

import java.util.List;

public interface MetrologicalDataServicePort {
    List<MetrologicalData> findAll();
    MetrologicalData findById(String id);
    MetrologicalData save(MetrologicalData metrologicalData);
    MetrologicalData update(String id, MetrologicalData metrologicalData);
    void delete(String id);
}

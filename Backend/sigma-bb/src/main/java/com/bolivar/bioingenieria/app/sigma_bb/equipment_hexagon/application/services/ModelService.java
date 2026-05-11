package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.ModelServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output.ModelPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService implements ModelServicePort {
    private final ModelPersistencePort modelPersistencePort;

    @Autowired
    public ModelService(ModelPersistencePort modelPersistencePort) {
        this.modelPersistencePort = modelPersistencePort;
    }

    @Override
    public List<Model> findAll() {
        return modelPersistencePort.findAll();
    }

    @Override
    public Model findById(String id) {
        return modelPersistencePort.findById(id);
    }

    @Override
    public Model save(Model model) {
        return modelPersistencePort.save(model);
    }

    @Override
    public Model update(String id, Model model) {
        return modelPersistencePort.update(id, model);
    }

    @Override
    public void delete(String id) {
        modelPersistencePort.delete(id);
    }
}

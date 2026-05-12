package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Model;

import java.util.List;

public interface ModelServicePort {
    List<Model> findAll();
    Model findById(String id);
    Model save(Model model);
    Model update(String id, Model model);
    void delete(String id);
}

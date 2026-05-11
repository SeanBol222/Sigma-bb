package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.TechnicalVerification;

import java.util.List;

public interface TechnicalVerificationPersistencePort {
    List<TechnicalVerification> findAll();
    TechnicalVerification findById(String id);
    TechnicalVerification save(TechnicalVerification technicalVerification);
    TechnicalVerification update(String id, TechnicalVerification technicalVerification);
    void delete(String id);
}

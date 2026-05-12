package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.TechnicalVerification;

import java.util.List;

public interface TechnicalVerificationServicePort {
    List<TechnicalVerification> findAll();
    TechnicalVerification findById(String id);
    TechnicalVerification save(TechnicalVerification technicalVerification);
    TechnicalVerification update(String id, TechnicalVerification technicalVerification);
    void delete(String id);
}

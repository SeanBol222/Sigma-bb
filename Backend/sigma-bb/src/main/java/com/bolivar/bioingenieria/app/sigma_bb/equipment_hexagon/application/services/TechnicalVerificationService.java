package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.TechnicalVerificationServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output.TechnicalVerificationPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.TechnicalVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicalVerificationService implements TechnicalVerificationServicePort {
    private final TechnicalVerificationPersistencePort technicalVerificationPersistencePort;

    @Autowired
    public TechnicalVerificationService(TechnicalVerificationPersistencePort technicalVerificationPersistencePort) {
        this.technicalVerificationPersistencePort = technicalVerificationPersistencePort;
    }

    @Override
    public List<TechnicalVerification> findAll() {
        return technicalVerificationPersistencePort.findAll();
    }

    @Override
    public TechnicalVerification findById(String id) {
        return this.technicalVerificationPersistencePort.findById(id);
    }

    @Override
    public TechnicalVerification save(TechnicalVerification technicalVerification) {
        return technicalVerificationPersistencePort.save(technicalVerification);
    }

    @Override
    public TechnicalVerification update(String id, TechnicalVerification technicalVerification) {
        return technicalVerificationPersistencePort.update(id, technicalVerification);
    }

    @Override
    public void delete(String id) {
        technicalVerificationPersistencePort.delete(id);
    }
}

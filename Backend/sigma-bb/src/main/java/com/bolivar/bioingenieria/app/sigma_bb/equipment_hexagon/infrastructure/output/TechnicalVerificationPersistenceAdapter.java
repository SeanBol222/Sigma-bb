package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output.TechnicalVerificationPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.TechnicalVerification;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities.TechnicalVerificationEntity;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.errors.TechnicalVerificationNotFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.mapper.TechnicalVerificationPersistenceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.repository.SpringTechnicalVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TechnicalVerificationPersistenceAdapter implements TechnicalVerificationPersistencePort {
    private final SpringTechnicalVerificationRepository springTechnicalVerificationRepository;
    private final TechnicalVerificationPersistenceMapper technicalVerificationPersistenceMapper;

    @Autowired
    public TechnicalVerificationPersistenceAdapter(SpringTechnicalVerificationRepository springTechnicalVerificationRepository,
                                                   TechnicalVerificationPersistenceMapper technicalVerificationPersistenceMapper) {
        this.springTechnicalVerificationRepository = springTechnicalVerificationRepository;
        this.technicalVerificationPersistenceMapper = technicalVerificationPersistenceMapper;
    }

    @Override
    public List<TechnicalVerification> findAll() {
        List<TechnicalVerificationEntity> entities = springTechnicalVerificationRepository.findAll();
        return entities.stream()
                .map(technicalVerificationPersistenceMapper::toTechnicalVerification).toList();
    }

    @Override
    public TechnicalVerification findById(String id) {
        UUID uuid = UUID.fromString(id);
        TechnicalVerificationEntity entity = springTechnicalVerificationRepository.findById(uuid)
                .orElseThrow(() -> new TechnicalVerificationNotFoundException(id));
        return technicalVerificationPersistenceMapper.toTechnicalVerification(entity);
    }

    @Override
    public TechnicalVerification save(TechnicalVerification technicalVerification) {
        TechnicalVerificationEntity entity = technicalVerificationPersistenceMapper.toTechnicalVerificationEntity(technicalVerification);
        TechnicalVerificationEntity saved = springTechnicalVerificationRepository.save(entity);
        return technicalVerificationPersistenceMapper.toTechnicalVerification(saved);
    }

    @Override
    public TechnicalVerification update(String id, TechnicalVerification technicalVerification) {
        UUID uuid = UUID.fromString(id);
        TechnicalVerificationEntity existing = springTechnicalVerificationRepository.findById(uuid)
                .orElseThrow(() -> new TechnicalVerificationNotFoundException(id));

        technicalVerificationPersistenceMapper.updateEntityFromDomain(technicalVerification, existing);

        TechnicalVerificationEntity saved = springTechnicalVerificationRepository.save(existing);

        return technicalVerificationPersistenceMapper.toTechnicalVerification(saved);
    }

    @Override
    public void delete(String id) {
        UUID uuid = UUID.fromString(id);
        if (!springTechnicalVerificationRepository.existsById(uuid)) {
            throw new TechnicalVerificationNotFoundException(id);
        }
        springTechnicalVerificationRepository.deleteById(uuid);
    }
}

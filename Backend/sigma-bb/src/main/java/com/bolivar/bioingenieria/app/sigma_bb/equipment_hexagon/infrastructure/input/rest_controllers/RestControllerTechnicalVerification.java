package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.rest_controllers;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.TechnicalVerificationServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.TechnicalVerification;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.mapper.TechnicalVerificationRestMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request.TechnicalVerificationRequest;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.response.TechnicalVerificationResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/technical-verifications")
public class RestControllerTechnicalVerification {
    private final TechnicalVerificationServicePort technicalVerificationServicePort;
    private final TechnicalVerificationRestMapper technicalVerificationRestMapper;

    @Autowired
    public RestControllerTechnicalVerification(TechnicalVerificationServicePort technicalVerificationServicePort,
                                               TechnicalVerificationRestMapper technicalVerificationRestMapper) {
        this.technicalVerificationServicePort = technicalVerificationServicePort;
        this.technicalVerificationRestMapper = technicalVerificationRestMapper;
    }

    @GetMapping
    public ResponseEntity<List<TechnicalVerificationResponse>> getAllTechnicalVerifications() {
        List<TechnicalVerificationResponse> response = technicalVerificationRestMapper
                .toTechnicalVerificationResponseList(technicalVerificationServicePort.findAll());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicalVerificationResponse> getTechnicalVerification(@PathVariable String id) {
        TechnicalVerificationResponse response = technicalVerificationRestMapper
                .toTechnicalVerificationResponse(technicalVerificationServicePort.findById(id));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TechnicalVerificationResponse> createTechnicalVerification(
            @Valid @RequestBody TechnicalVerificationRequest request) {

        TechnicalVerification technicalVerification = technicalVerificationRestMapper.toTechnicalVerification(request);

        TechnicalVerification created = technicalVerificationServicePort.save(technicalVerification);

        TechnicalVerificationResponse response = technicalVerificationRestMapper.toTechnicalVerificationResponse(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnicalVerificationResponse> updateTechnicalVerification(
            @PathVariable String id,
            @Valid @RequestBody TechnicalVerificationRequest request) {

        TechnicalVerification technicalVerification = technicalVerificationRestMapper.toTechnicalVerification(request);

        TechnicalVerification updated = technicalVerificationServicePort.update(id, technicalVerification);

        TechnicalVerificationResponse response = technicalVerificationRestMapper.toTechnicalVerificationResponse(updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnicalVerification(@PathVariable String id) {
        technicalVerificationServicePort.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.rest_controllers;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.TechnicalVerificationServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.technical_verification_services.commands.CreateTechnicalVerificationCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.technical_verification_services.commands.DeleteTechnicalVerificationCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.technical_verification_services.commands.UpdateTechnicalVerificationCommand;
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
    private final TechnicalVerificationServicePort servicePort;
    private final TechnicalVerificationRestMapper restMapper;

    @Autowired
    public RestControllerTechnicalVerification(TechnicalVerificationServicePort servicePort,
                                               TechnicalVerificationRestMapper restMapper) {
        this.servicePort = servicePort;
        this.restMapper = restMapper;
    }

    @GetMapping
    public ResponseEntity<List<TechnicalVerificationResponse>> getAll() {
        return ResponseEntity.ok(restMapper.toTechnicalVerificationResponseList(servicePort.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicalVerificationResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(restMapper.toTechnicalVerificationResponse(servicePort.findById(id)));
    }

    @PostMapping
    public ResponseEntity<TechnicalVerificationResponse> create(@Valid @RequestBody TechnicalVerificationRequest request) {
        CreateTechnicalVerificationCommand cmd = new CreateTechnicalVerificationCommand(
                request.getDescription(), request.getVerificationType());
        TechnicalVerification created = servicePort.save(cmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(restMapper.toTechnicalVerificationResponse(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnicalVerificationResponse> update(
            @PathVariable String id, @Valid @RequestBody TechnicalVerificationRequest request) {
        UpdateTechnicalVerificationCommand cmd = new UpdateTechnicalVerificationCommand(
                request.getDescription(), request.getVerificationType());
        TechnicalVerification updated = servicePort.update(id, cmd);
        return ResponseEntity.ok(restMapper.toTechnicalVerificationResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        servicePort.delete(new DeleteTechnicalVerificationCommand(id));
        return ResponseEntity.noContent().build();
    }
}

package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.rest_controllers;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.EquipmentServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.equipment_services.commands.CreateEquipmentCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.equipment_services.commands.DeleteEquipmentCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.equipment_services.commands.UpdateEquipmentCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Equipment;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.mapper.EquipmentRestMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request.EquipmentRequest;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.response.EquipmentReponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/equipment")
public class RestControllerEquipment {
    private final EquipmentServicePort servicePort;
    private final EquipmentRestMapper restMapper;

    @Autowired
    public RestControllerEquipment(EquipmentServicePort servicePort,
                                   EquipmentRestMapper restMapper) {
        this.servicePort = servicePort;
        this.restMapper = restMapper;
    }

    @GetMapping
    public ResponseEntity<List<EquipmentReponse>> getAll() {
        return ResponseEntity.ok(restMapper.toEquipmentResponseList(servicePort.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentReponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(restMapper.toEquipmentResponse(servicePort.findById(id)));
    }

    @PostMapping
    public ResponseEntity<EquipmentReponse> create(@Valid @RequestBody EquipmentRequest request) {
        CreateEquipmentCommand cmd = new CreateEquipmentCommand(
                request.getEquipmentTypeId(), request.getBrandId());
        Equipment created = servicePort.save(cmd);
        System.out.println(request + "\n\n\n\n\n\n\n");
        System.out.println(created + "\n\n\n\n\n\n\n");
        System.out.println(restMapper.toEquipmentResponse(created) + "\n\n\n\n\n\n\n");
        return ResponseEntity.status(HttpStatus.CREATED).body(restMapper.toEquipmentResponse(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentReponse> update(
            @PathVariable String id, @Valid @RequestBody EquipmentRequest request) {
        UpdateEquipmentCommand cmd = new UpdateEquipmentCommand(
                request.getEquipmentTypeId(), request.getBrandId());
        Equipment updated = servicePort.update(id, cmd);
        return ResponseEntity.ok(restMapper.toEquipmentResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        servicePort.delete(new DeleteEquipmentCommand(id));
        return ResponseEntity.noContent().build();
    }
}

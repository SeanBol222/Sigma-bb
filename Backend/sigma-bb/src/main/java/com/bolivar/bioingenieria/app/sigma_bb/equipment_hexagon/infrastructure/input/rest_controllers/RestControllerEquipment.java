package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.rest_controllers;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.EquipmentServicePort;
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
    private final EquipmentServicePort equipmentServicePort;
    private final EquipmentRestMapper equipmentRestMapper;

    @Autowired
    public RestControllerEquipment(EquipmentServicePort equipmentServicePort,
                                   EquipmentRestMapper equipmentRestMapper) {
        this.equipmentServicePort = equipmentServicePort;
        this.equipmentRestMapper = equipmentRestMapper;
    }

    @GetMapping
    public ResponseEntity<List<EquipmentReponse>> getAllEquipment() {
        List<EquipmentReponse> response = equipmentRestMapper
                .toEquipmentResponseList(equipmentServicePort.findAll());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentReponse> getEquipment(@PathVariable String id) {
        EquipmentReponse response = equipmentRestMapper
                .toEquipmentResponse(equipmentServicePort.findById(id));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<EquipmentReponse> createEquipment(
            @Valid @RequestBody EquipmentRequest request) {

        Equipment equipment = equipmentRestMapper.toEquipment(request);

        Equipment created = equipmentServicePort.save(equipment);

        EquipmentReponse response = equipmentRestMapper.toEquipmentResponse(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentReponse> updateEquipment(
            @PathVariable String id,
            @Valid @RequestBody EquipmentRequest request) {

        Equipment equipment = equipmentRestMapper.toEquipment(request);

        Equipment updated = equipmentServicePort.update(id, equipment);

        EquipmentReponse response = equipmentRestMapper.toEquipmentResponse(updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable String id) {
        equipmentServicePort.delete(id);
        return ResponseEntity.noContent().build();
    }
}

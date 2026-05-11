package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.rest_controllers;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.EquipmentTypeServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.EquipmentType;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.mapper.EquipmentTypeRestMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request.EquipmentTypeRequest;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.response.EquipmentTypeResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/equipment-types")
public class RestControllerEquipmentType {
    private final EquipmentTypeServicePort equipmentTypeServicePort;
    private final EquipmentTypeRestMapper equipmentTypeRestMapper;

    @Autowired
    public RestControllerEquipmentType(EquipmentTypeServicePort equipmentTypeServicePort, EquipmentTypeRestMapper equipmentTypeRestMapper) {
        this.equipmentTypeServicePort = equipmentTypeServicePort;
        this.equipmentTypeRestMapper = equipmentTypeRestMapper;
    }

    @GetMapping
    public ResponseEntity<List<EquipmentTypeResponse>> getAllEquipmentTypes() {
        List<EquipmentTypeResponse> response = equipmentTypeRestMapper
                .toEquipmentTypeResponseList(equipmentTypeServicePort.findAll());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentTypeResponse> getEquipmentType(@PathVariable String id) {
        EquipmentTypeResponse response = equipmentTypeRestMapper
                .toEquipmentTypeResponse(equipmentTypeServicePort.findById(id));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<EquipmentTypeResponse> createEquipmentType(
            @Valid @RequestBody EquipmentTypeRequest request) {

        EquipmentType equipmentType = equipmentTypeRestMapper.toEquipmentType(request);

        EquipmentType created = equipmentTypeServicePort.save(equipmentType);

        EquipmentTypeResponse response = equipmentTypeRestMapper.toEquipmentTypeResponse(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentTypeResponse> updateEquipmentType(
            @PathVariable String id,
            @Valid @RequestBody EquipmentTypeRequest request) {

        EquipmentType equipmentType = equipmentTypeRestMapper.toEquipmentType(request);

        EquipmentType updated = equipmentTypeServicePort.update(id, equipmentType);

        EquipmentTypeResponse response = equipmentTypeRestMapper.toEquipmentTypeResponse(updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipmentType(@PathVariable String id) {
        equipmentTypeServicePort.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.rest_controllers;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.EquipmentTypeServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.equipment_type_services.commands.CreateEquipmentTypeCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.equipment_type_services.commands.DeleteEquipmentTypeCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.equipment_type_services.commands.UpdateEquipmentTypeCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.metrological_data_services.commands.MetrologicalDataCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.EquipmentType;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.MetrologicalData;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.mapper.EquipmentTypeRestMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request.EquipmentTypeRequest;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request.MetrologicalDataRequest;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request.UpdateMetrologicalDataBody;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request.UpdateMetrologicalDataListBody;
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
    public RestControllerEquipmentType(EquipmentTypeServicePort equipmentTypeServicePort,
                                       EquipmentTypeRestMapper equipmentTypeRestMapper) {
        this.equipmentTypeServicePort = equipmentTypeServicePort;
        this.equipmentTypeRestMapper = equipmentTypeRestMapper;
    }

    @GetMapping
    public ResponseEntity<List<EquipmentTypeResponse>> getAllEquipmentTypes() {
        return ResponseEntity.ok(equipmentTypeRestMapper
                .toEquipmentTypeResponseList(equipmentTypeServicePort.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentTypeResponse> getEquipmentType(@PathVariable String id) {
        return ResponseEntity.ok(equipmentTypeRestMapper
                .toEquipmentTypeResponse(equipmentTypeServicePort.findById(id)));
    }

    @PostMapping
    public ResponseEntity<EquipmentTypeResponse> createEquipmentType(
            @Valid @RequestBody EquipmentTypeRequest request) {
        CreateEquipmentTypeCommand command = new CreateEquipmentTypeCommand(
                request.getEquipmentTypeName(), request.getTechnicalDefinition(),
                request.getCareRecommendations(), request.getVoltage(), request.getAmperage(),
                request.getPredominantTechnology(), request.getVerifiable(), request.getUnitMaintenanceValue(),
                equipmentTypeRestMapper.toEquipmentType(request).getMetrologicalData());
        EquipmentType created = equipmentTypeServicePort.save(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(equipmentTypeRestMapper.toEquipmentTypeResponse(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentTypeResponse> updateEquipmentType(
            @PathVariable String id,
            @Valid @RequestBody EquipmentTypeRequest request) {
        UpdateEquipmentTypeCommand command = new UpdateEquipmentTypeCommand(
                request.getEquipmentTypeName(), request.getTechnicalDefinition(),
                request.getCareRecommendations(), request.getVoltage(), request.getAmperage(),
                request.getPredominantTechnology(), request.getVerifiable(), request.getUnitMaintenanceValue());
        EquipmentType updated = equipmentTypeServicePort.update(id, command);
        return ResponseEntity.ok(equipmentTypeRestMapper.toEquipmentTypeResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipmentType(@PathVariable String id) {
        equipmentTypeServicePort.delete(new DeleteEquipmentTypeCommand(id));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/metrological-data")
    public ResponseEntity<EquipmentTypeResponse> addMetrologicalData(
            @PathVariable String id,
            @Valid @RequestBody MetrologicalDataRequest request) {
        MetrologicalDataCommand cmd = new MetrologicalDataCommand(request.getValue(), request.getType());
        EquipmentType result = equipmentTypeServicePort.addMetrologicalData(id, cmd);
        return ResponseEntity.ok(equipmentTypeRestMapper.toEquipmentTypeResponse(result));
    }

    @DeleteMapping("/{id}/metrological-data")
    public ResponseEntity<EquipmentTypeResponse> removeMetrologicalData(
            @PathVariable String id,
            @Valid @RequestBody MetrologicalDataRequest request) {
        MetrologicalDataCommand cmd = new MetrologicalDataCommand(request.getValue(), request.getType());
        EquipmentType result = equipmentTypeServicePort.removeMetrologicalData(id, cmd);
        return ResponseEntity.ok(equipmentTypeRestMapper.toEquipmentTypeResponse(result));
    }

    @PutMapping("/{id}/metrological-data")
    public ResponseEntity<EquipmentTypeResponse> updateMetrologicalData(
            @PathVariable String id,
            @Valid @RequestBody UpdateMetrologicalDataBody body) {
        MetrologicalDataCommand oldCmd = new MetrologicalDataCommand(
                body.getOldData().getValue(), body.getOldData().getType());
        MetrologicalDataCommand newCmd = new MetrologicalDataCommand(
                body.getNewData().getValue(), body.getNewData().getType());
        EquipmentType result = equipmentTypeServicePort.updateMetrologicalData(id, oldCmd, newCmd);
        return ResponseEntity.ok(equipmentTypeRestMapper.toEquipmentTypeResponse(result));
    }

    @PostMapping("/{id}/metrological-data/batch")
    public ResponseEntity<EquipmentTypeResponse> addMetrologicalDataList(
            @PathVariable String id,
            @Valid @RequestBody List<MetrologicalDataRequest> requests) {
        List<MetrologicalDataCommand> cmds = requests.stream()
                .map(r -> new MetrologicalDataCommand(r.getValue(), r.getType())).toList();
        EquipmentType result = equipmentTypeServicePort.addMetrologicalDataList(id, cmds);
        return ResponseEntity.ok(equipmentTypeRestMapper.toEquipmentTypeResponse(result));
    }

    @DeleteMapping("/{id}/metrological-data/batch")
    public ResponseEntity<EquipmentTypeResponse> removeMetrologicalDataList(
            @PathVariable String id,
            @Valid @RequestBody List<MetrologicalDataRequest> requests) {
        List<MetrologicalDataCommand> cmds = requests.stream()
                .map(r -> new MetrologicalDataCommand(r.getValue(), r.getType())).toList();
        EquipmentType result = equipmentTypeServicePort.removeMetrologicalDataList(id, cmds);
        return ResponseEntity.ok(equipmentTypeRestMapper.toEquipmentTypeResponse(result));
    }

    @PutMapping("/{id}/metrological-data/batch")
    public ResponseEntity<EquipmentTypeResponse> updateMetrologicalDataList(
            @PathVariable String id,
            @Valid @RequestBody UpdateMetrologicalDataListBody body) {
        List<MetrologicalDataCommand> oldCmds = body.getOldData().stream()
                .map(r -> new MetrologicalDataCommand(r.getValue(), r.getType())).toList();
        List<MetrologicalDataCommand> newCmds = body.getNewData().stream()
                .map(r -> new MetrologicalDataCommand(r.getValue(), r.getType())).toList();
        EquipmentType result = equipmentTypeServicePort.updateMetrologicalDataList(id, oldCmds, newCmds);
        return ResponseEntity.ok(equipmentTypeRestMapper.toEquipmentTypeResponse(result));
    }
}

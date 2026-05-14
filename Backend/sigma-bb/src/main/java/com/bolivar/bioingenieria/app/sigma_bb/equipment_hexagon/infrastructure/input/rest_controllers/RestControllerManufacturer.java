package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.rest_controllers;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.ManufacturerServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.manufacturer_services.commands.CreateManufacturerCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.manufacturer_services.commands.DeleteManufacturerCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.manufacturer_services.commands.UpdateManufacturerCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Manufacturer;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.mapper.ManufacturerRestMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request.ManufacturerRequest;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.response.ManufacturerResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/manufacturers")
public class RestControllerManufacturer {
    private final ManufacturerServicePort manufacturerServicePort;
    private final ManufacturerRestMapper manufacturerRestMapper;

    @Autowired
    public RestControllerManufacturer(ManufacturerServicePort manufacturerServicePort, ManufacturerRestMapper manufacturerRestMapper) {
        this.manufacturerServicePort = manufacturerServicePort;
        this.manufacturerRestMapper = manufacturerRestMapper;
    }

    @GetMapping
    public ResponseEntity<List<ManufacturerResponse>> getAllManufacturers() {
        List<ManufacturerResponse> response = manufacturerRestMapper
                .toManufacturerResponseList(manufacturerServicePort.findAll());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerResponse> getManufacturer(@PathVariable String id) {
        ManufacturerResponse response = manufacturerRestMapper
                .toManufacturerResponse(manufacturerServicePort.findById(id));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ManufacturerResponse> createManufacturer(
            @Valid @RequestBody ManufacturerRequest request) {

        CreateManufacturerCommand command = new CreateManufacturerCommand(request.getName(), request.getCountryId());
        Manufacturer created = manufacturerServicePort.save(command);

        ManufacturerResponse response = manufacturerRestMapper.toManufacturerResponse(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManufacturerResponse> updateManufacturer(
            @PathVariable String id,
            @Valid @RequestBody ManufacturerRequest request) {

        UpdateManufacturerCommand command = new UpdateManufacturerCommand(request.getName(), request.getCountryId());
        Manufacturer updated = manufacturerServicePort.update(id, command);

        ManufacturerResponse response = manufacturerRestMapper.toManufacturerResponse(updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable String id) {
        DeleteManufacturerCommand command = new DeleteManufacturerCommand(id);
        manufacturerServicePort.delete(command);
        return ResponseEntity.noContent().build();
    }
}

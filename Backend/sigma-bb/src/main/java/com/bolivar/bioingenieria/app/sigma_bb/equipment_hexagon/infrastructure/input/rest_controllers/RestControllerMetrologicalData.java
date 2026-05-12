package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.rest_controllers;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.MetrologicalDataServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.MetrologicalData;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.mapper.MetrologicalDataRestMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request.MetrologicalDataRequest;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.response.MetrologicalDataResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/metrological-data")
public class RestControllerMetrologicalData {
    private final MetrologicalDataServicePort metrologicalDataServicePort;
    private final MetrologicalDataRestMapper metrologicalDataRestMapper;

    @Autowired
    public RestControllerMetrologicalData(MetrologicalDataServicePort metrologicalDataServicePort,
                                          MetrologicalDataRestMapper metrologicalDataRestMapper) {
        this.metrologicalDataServicePort = metrologicalDataServicePort;
        this.metrologicalDataRestMapper = metrologicalDataRestMapper;
    }

    @GetMapping
    public ResponseEntity<List<MetrologicalDataResponse>> getAllMetrologicalData() {
        List<MetrologicalDataResponse> response = metrologicalDataRestMapper
                .toMetrologicalDataResponseList(metrologicalDataServicePort.findAll());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetrologicalDataResponse> getMetrologicalData(@PathVariable String id) {
        MetrologicalDataResponse response = metrologicalDataRestMapper
                .toMetrologicalDataResponse(metrologicalDataServicePort.findById(id));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<MetrologicalDataResponse> createMetrologicalData(
            @Valid @RequestBody MetrologicalDataRequest request) {

        MetrologicalData metrologicalData = metrologicalDataRestMapper.toMetrologicalData(request);

        MetrologicalData created = metrologicalDataServicePort.save(metrologicalData);

        MetrologicalDataResponse response = metrologicalDataRestMapper.toMetrologicalDataResponse(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetrologicalDataResponse> updateMetrologicalData(
            @PathVariable String id,
            @Valid @RequestBody MetrologicalDataRequest request) {

        MetrologicalData metrologicalData = metrologicalDataRestMapper.toMetrologicalData(request);

        MetrologicalData updated = metrologicalDataServicePort.update(id, metrologicalData);

        MetrologicalDataResponse response = metrologicalDataRestMapper.toMetrologicalDataResponse(updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetrologicalData(@PathVariable String id) {
        metrologicalDataServicePort.delete(id);
        return ResponseEntity.noContent().build();
    }
}

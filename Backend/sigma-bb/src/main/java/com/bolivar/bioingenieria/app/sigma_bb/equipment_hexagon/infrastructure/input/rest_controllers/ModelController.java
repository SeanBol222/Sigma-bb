package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.rest_controllers;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.ModelServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Model;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.mapper.ModelRestMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request.ModelRequest;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.response.ModelResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/models")
public class ModelController {

    private final ModelServicePort modelServicePort;
    private final ModelRestMapper modelRestMapper;

    @Autowired
    public ModelController(ModelServicePort modelServicePort, ModelRestMapper modelRestMapper) {
        this.modelServicePort = modelServicePort;
        this.modelRestMapper = modelRestMapper;
    }

    @GetMapping
    public ResponseEntity<List<ModelResponse>> getAllModels() {
        List<ModelResponse> response = modelRestMapper
                .toModelResponseList(modelServicePort.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelResponse> getModel(@PathVariable String id) {
        ModelResponse response = modelRestMapper
                .toModelResponse(modelServicePort.findById(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ModelResponse> createModel(@Valid @RequestBody ModelRequest request) {
        Model model = modelRestMapper.toModel(request);
        Model created = modelServicePort.save(model);
        ModelResponse response = modelRestMapper.toModelResponse(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelResponse> updateModel(
            @PathVariable String id,
            @Valid @RequestBody ModelRequest request) {
        Model model = modelRestMapper.toModel(request);
        Model updated = modelServicePort.update(id, model);
        ModelResponse response = modelRestMapper.toModelResponse(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable String id) {
        modelServicePort.delete(id);
        return ResponseEntity.noContent().build();
    }
}

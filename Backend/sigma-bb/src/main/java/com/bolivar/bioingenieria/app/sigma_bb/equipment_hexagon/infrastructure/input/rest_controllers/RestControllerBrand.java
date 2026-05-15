package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.rest_controllers;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.BrandServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.brand_services.commands.CreateBrandCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.brand_services.commands.DeleteBrandCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.brand_services.commands.UpdateBrandCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Brand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.mapper.BrandRestMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request.BrandRequest;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.response.BrandResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/brands")
public class RestControllerBrand {
    private final BrandServicePort brandServicePort;
    private final BrandRestMapper brandRestMapper;

    @Autowired
    public RestControllerBrand(BrandServicePort brandServicePort, BrandRestMapper brandRestMapper) {
        this.brandServicePort = brandServicePort;
        this.brandRestMapper = brandRestMapper;
    }

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getAllBrands() {
        List<BrandResponse> response = brandRestMapper
                .toBrandResponseList(brandServicePort.findAll());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getBrand(@PathVariable String id) {
        BrandResponse response = brandRestMapper
                .toBrandResponse(brandServicePort.findById(id));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BrandResponse> createBrand(
            @Valid @RequestBody BrandRequest request) {

        CreateBrandCommand command = new CreateBrandCommand(request.getName());
        Brand created = brandServicePort.save(command);

        BrandResponse response = brandRestMapper.toBrandResponse(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponse> updateBrand(
            @PathVariable String id,
            @Valid @RequestBody BrandRequest request) {

        UpdateBrandCommand command = new UpdateBrandCommand(request.getName());
        Brand updated = brandServicePort.update(id, command);

        BrandResponse response = brandRestMapper.toBrandResponse(updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable String id) {
        DeleteBrandCommand command = new DeleteBrandCommand(id);
        brandServicePort.delete(command);
        return ResponseEntity.noContent().build();
    }
}

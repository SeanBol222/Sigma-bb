package com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.infrastructure.input.rest_controllers;

import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.application.ports.input.CityServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.application.services.city_services.commands.CreateCityCommand;
import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.application.services.city_services.commands.DeleteCityCommand;
import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.application.services.city_services.commands.UpdateCityCommand;
import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.domain.city.City;
import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.infrastructure.input.mapper.CityRestMapper;
import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.infrastructure.input.model.request.CityRequest;
import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.infrastructure.input.model.response.CityResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/cities")
public class RestControllerCity {
    private final CityServicePort cityServicePort;
    private final CityRestMapper cityRestMapper;

    @Autowired
    public RestControllerCity(CityServicePort cityServicePort,
                              CityRestMapper cityRestMapper) {
        this.cityServicePort = cityServicePort;
        this.cityRestMapper = cityRestMapper;
    }

    @GetMapping
    public ResponseEntity<List<CityResponse>> getAllCities() {
        List<CityResponse> response = cityRestMapper
                .toCityResponseList(cityServicePort.findAll());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCity(@PathVariable String id) {
        CityResponse response = cityRestMapper
                .toCityResponse(cityServicePort.findById(id));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CityResponse> createCity(
            @Valid @RequestBody CityRequest request) {

        CreateCityCommand command = new CreateCityCommand(
                request.getName(),
                request.getCountryId()
        );

        City created = cityServicePort.save(command);

        CityResponse response = cityRestMapper.toCityResponse(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> updateCity(
            @PathVariable String id,
            @Valid @RequestBody CityRequest request) {

        UpdateCityCommand command = new UpdateCityCommand(
                request.getId(),
                request.getName(),
                request.getCountryId()
        );

        City updated = cityServicePort.update(id, command);

        CityResponse response = cityRestMapper.toCityResponse(updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable String id) {
        DeleteCityCommand command = new DeleteCityCommand(id);
        cityServicePort.delete(command);
        return ResponseEntity.noContent().build();
    }
}

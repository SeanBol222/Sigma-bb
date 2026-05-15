package com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.infrastructure.input.rest_controllers;

import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.application.ports.input.CountryServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.application.services.country_services.commands.CreateCountryCommand;
import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.application.services.country_services.commands.DeleteCountryCommand;
import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.application.services.country_services.commands.UpdateCountryCommand;
import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.domain.country.Country;
import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.infrastructure.input.mapper.CountryRestMapper;
import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.infrastructure.input.model.request.CountryRequest;
import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.infrastructure.input.model.response.CountryResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/countries")
public class RestControllerCountry {
    private final CountryServicePort countryServicePort;
    private final CountryRestMapper countryRestMapper;

    @Autowired
    public RestControllerCountry(CountryServicePort countryServicePort,
                                  CountryRestMapper countryRestMapper) {
        this.countryServicePort = countryServicePort;
        this.countryRestMapper = countryRestMapper;
    }

    @GetMapping
    public ResponseEntity<List<CountryResponse>> getAllCountries() {
        List<CountryResponse> response = countryRestMapper
                .toCountryResponseList(countryServicePort.findAll());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> getCountry(@PathVariable String id) {
        CountryResponse response = countryRestMapper
                .toCountryResponse(countryServicePort.findById(id));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CountryResponse> createCountry(
            @Valid @RequestBody CountryRequest request) {

        CreateCountryCommand command = new CreateCountryCommand(request.getId(), request.getName());
        Country created = countryServicePort.save(command);

        CountryResponse response = countryRestMapper.toCountryResponse(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryResponse> updateCountry(
            @PathVariable String id,
            @Valid @RequestBody CountryRequest request) {

        UpdateCountryCommand command = new UpdateCountryCommand(request.getId(), request.getName());
        Country updated = countryServicePort.update(id, command);

        CountryResponse response = countryRestMapper.toCountryResponse(updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable String id) {
        DeleteCountryCommand command = new DeleteCountryCommand(id);
        countryServicePort.delete(command);
        return ResponseEntity.noContent().build();
    }
}

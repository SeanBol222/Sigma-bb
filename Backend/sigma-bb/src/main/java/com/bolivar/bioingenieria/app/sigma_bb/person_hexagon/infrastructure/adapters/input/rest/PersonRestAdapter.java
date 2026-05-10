package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.ports.input.PersonServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.mapper.PersonRestMapper;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.request.PersonCreateRequest;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.response.PersonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonRestAdapter {

    private final PersonServicePort personServicePort;
    private final PersonRestMapper personRestMapper;
    // Aquí irían los métodos para manejar las solicitudes HTTP (GET, POST, PUT, DELETE)
    // y utilizarían personServicePort para interactuar con la lógica de negocio
    // y personRestMapper para convertir entre entidades de dominio y DTOs de respuesta.

    @GetMapping("/v1/api")
    public List<?> getAllPersons() {
        return personRestMapper.toPersonResponseList(personServicePort.findAll());
    }

    @GetMapping("/v1/api/{id}")
    public Object getPersonById(@PathVariable UUID id) {
        return personRestMapper.toPersonResponse(personServicePort.findById(id));
    }

    @PostMapping("/v1/api")
    public ResponseEntity<PersonResponse> createPerson(@Valid @RequestBody PersonCreateRequest personCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personRestMapper.toPersonResponse(
                        personServicePort.save(personRestMapper.toPerson(personCreateRequest))));
    }

    @PutMapping("/v1/api/{id}")
    public ResponseEntity<PersonResponse> updatePerson(
            @PathVariable UUID id,
            @Valid @RequestBody PersonCreateRequest personCreateRequest) {

        return ResponseEntity.ok(
                personRestMapper.toPersonResponse(
                        personServicePort.update(id, personRestMapper.toPerson(personCreateRequest))));
    }

    @DeleteMapping("/v1/api/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        personServicePort.delete(id);
        return ResponseEntity.noContent().build();
    }
}

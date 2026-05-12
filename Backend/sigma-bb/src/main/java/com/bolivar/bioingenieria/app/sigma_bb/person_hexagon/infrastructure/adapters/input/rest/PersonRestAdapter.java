package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.ports.input.PersonServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.mapper.EmailPersonRestMapper;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.mapper.PersonRestMapper;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.mapper.PhonePersonRestMapper;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.request.EmailPersonCreateRequest;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.request.PersonCreateRequest;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.request.PersonUpdateRequest;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.request.PhonePersonCreateRequest;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.response.PersonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonRestAdapter {

    @Qualifier("personService")
    private final PersonServicePort personServicePort;

    private final PersonRestMapper personRestMapper;
    private final PhonePersonRestMapper phonePersonRestMapper;
    private final EmailPersonRestMapper emailPersonRestMapper;

    // -------------------------------------------------------
    // -------------------- CRUD PERSONA ---------------------
    // -------------------------------------------------------

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/v1/api")
    public List<?> getAllPersons() {
        return personRestMapper.toPersonResponseList(personServicePort.findAll());
    }

    @GetMapping("/v1/api/{id}")
    public Object getPersonById(@PathVariable UUID id) {
        return personRestMapper.toPersonResponse(personServicePort.findById(id));
    }

    @PostMapping("/v1/api")
    public ResponseEntity<?> createPerson(
            @Valid @RequestBody PersonCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personRestMapper.toPersonResponse(
                        personServicePort.save(personRestMapper.toPerson(request))));
    }

    @PutMapping("/v1/api/{id}")
    public ResponseEntity<PersonResponse> updatePerson(
            @PathVariable UUID id,
            @Valid @RequestBody PersonUpdateRequest request) {
        return ResponseEntity.ok(
                personRestMapper.toPersonResponse(
                        personServicePort.update(id, personRestMapper.tuUpdatePerson(request))));
    }

    @DeleteMapping("/v1/api/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        personServicePort.delete(id);
        return ResponseEntity.noContent().build();
    }

    // -------------------------------------------------------
    // -------------------- CRUD TELEFONO ---------------------
    // -------------------------------------------------------

    @PutMapping("/phone/v1/api/{id}")
    public ResponseEntity<PersonResponse> createPhonePerson(
            @PathVariable UUID id,
            @Valid @RequestBody PhonePersonCreateRequest request) {

        return ResponseEntity.ok(
                personRestMapper.toPersonResponse(
                        personServicePort.addPhone(id, phonePersonRestMapper.toPhonePerson(request))));
    }

    @PutMapping("/phone/v1/api/{id}/{phoneId}")
    public ResponseEntity<PersonResponse> updatePhonePerson(
            @PathVariable UUID id,
            @PathVariable UUID phoneId,
            @Valid @RequestBody PhonePersonCreateRequest request) {
        return ResponseEntity.ok(
                personRestMapper.toPersonResponse(
                        personServicePort.updatePhone(id, phoneId, phonePersonRestMapper.toPhonePerson(request))));
    }

    @DeleteMapping("/phone/v1/api/{id}/{phoneId}")
    public ResponseEntity<PersonResponse> deletePhonePerson(
            @PathVariable UUID id,
            @PathVariable UUID phoneId) {

        return ResponseEntity.ok(
                personRestMapper.toPersonResponse(
                        personServicePort.removePhone(id, phoneId)));
    }

    // -------------------------------------------------------
    // -------------------- CRUD EMAIL ---------------------
    // -------------------------------------------------------

    @PutMapping("/email/v1/api/{id}")
    public ResponseEntity<PersonResponse> createEmailPerson(
            @PathVariable UUID id,
            @Valid @RequestBody EmailPersonCreateRequest request) {

        return ResponseEntity.ok(
                personRestMapper.toPersonResponse(
                        personServicePort.addEmail(id, emailPersonRestMapper.toEmailPerson(request))));
    }

    @PutMapping("/email/v1/api/{id}/{emailId}")
    public ResponseEntity<PersonResponse> updateEmailPerson(
            @PathVariable UUID id,
            @PathVariable UUID emailId,
            @Valid @RequestBody EmailPersonCreateRequest request) {
        return ResponseEntity.ok(
                personRestMapper.toPersonResponse(
                        personServicePort.updateEmail(id, emailId, emailPersonRestMapper.toEmailPerson(request))));
    }

    @DeleteMapping("/email/v1/api/{id}/{emailId}")
    public ResponseEntity<PersonResponse> deleteEmailPerson(
            @PathVariable UUID id,
            @PathVariable UUID emailId) {
        return ResponseEntity.ok(
                personRestMapper.toPersonResponse(
                        personServicePort.removeEmail(id, emailId)));
    }
}

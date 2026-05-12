package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.service;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.ports.input.PersonServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.ports.output.PersonPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.exception.PersonNotFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.EmailPerson;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.Person;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.PhonePerson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class  PersonService implements PersonServicePort {

    private final PersonPersistencePort personPersistencePort;

    @Override
    public Person findById(UUID id) {
        return personPersistencePort.findById(id)
                .orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public List<Person> findAll() {
        return personPersistencePort.findAll();
    }

    @Override
    public Person save(Person person) {

        person.setIdentificador(UUID.randomUUID());

        for(PhonePerson phone : person.getPhonePersonList()) {
            phone.setIdTelefonoPersona(UUID.randomUUID());
        }

        for(EmailPerson email : person.getEmailPersonList()) {
            email.setIdCorreoPersona(UUID.randomUUID());
        }

        return personPersistencePort.save(person);
    }

    @Override
    public Person update(UUID id, Person person) {
        return personPersistencePort.findById(id)
                .map(existingPerson -> {
                    existingPerson.setCedula(person.getCedula());
                    existingPerson.setPrimerNombre(person.getPrimerNombre());
                    existingPerson.setSegundoNombre(person.getSegundoNombre());
                    existingPerson.setPrimerApellido(person.getPrimerApellido());
                    existingPerson.setSegundoApellido(person.getSegundoApellido());
                    existingPerson.setTipoPersona(person.getTipoPersona());
                    existingPerson.setSegundoTipoPersona(person.getSegundoTipoPersona());
                    return personPersistencePort.save(existingPerson);
                })
                .orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public void delete(UUID id) {
        if (!personPersistencePort.findById(id).isPresent()) {
            throw new PersonNotFoundException();
        }
        personPersistencePort.delete(id);
    }

    @Override
    public Person addEmail(UUID personId, EmailPerson email) {
        return personPersistencePort.findById(personId)
                .map(existingPerson -> {
                    email.setIdCorreoPersona(UUID.randomUUID());
                    existingPerson.getEmailPersonList().add(email);
                    return personPersistencePort.save(existingPerson);
                })
                .orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public Person addPhone(UUID personId, PhonePerson phone) {
        return personPersistencePort.findById(personId)
                .map(existingPerson -> {
                    phone.setIdTelefonoPersona(UUID.randomUUID());
                    existingPerson.getPhonePersonList().add(phone);
                    return personPersistencePort.save(existingPerson);
                })
                .orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public Person updateEmail(UUID personId, UUID emailId, EmailPerson email) {
        return personPersistencePort.findById(personId)
                .map(existingPerson -> {
                    existingPerson.getEmailPersonList().stream()
                            .filter(e -> e.getIdCorreoPersona().equals(emailId))
                            .findFirst()
                            .ifPresent(e -> {
                                e.setCorreoPersona(email.getCorreoPersona());
                            });
                    return personPersistencePort.save(existingPerson);
                })
                .orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public Person updatePhone(UUID personId, UUID phoneId, PhonePerson phone) {
        return personPersistencePort.findById(personId)
                .map(existingPerson -> {
                    existingPerson.getPhonePersonList().stream()
                            .filter(p -> p.getIdTelefonoPersona().equals(phoneId))
                            .findFirst()
                            .ifPresent(p -> {
                                p.setTelefonoPersona(phone.getTelefonoPersona());
                            });
                    return personPersistencePort.save(existingPerson);
                })
                .orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public Person removeEmail(UUID personId, UUID emailId) {
        return personPersistencePort.findById(personId)
                .map(existingPerson -> {
                    existingPerson.getEmailPersonList().removeIf(e ->
                            e.getIdCorreoPersona().equals(emailId));
                    return personPersistencePort.save(existingPerson);
                })
                .orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public Person removePhone(UUID personId, UUID phoneId) {
        return personPersistencePort.findById(personId)
                .map(existingPerson -> {
                    existingPerson.getPhonePersonList().removeIf(p ->
                            p.getIdTelefonoPersona().equals(phoneId));
                    return personPersistencePort.save(existingPerson);
                })
                .orElseThrow(PersonNotFoundException::new);
    }


}

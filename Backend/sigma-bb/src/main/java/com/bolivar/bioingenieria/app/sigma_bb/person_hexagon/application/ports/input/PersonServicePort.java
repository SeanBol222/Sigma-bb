package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.ports.input;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.EmailPerson;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.Person;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.PhonePerson;

import java.util.List;
import java.util.UUID;

public interface PersonServicePort {
    Person findById(UUID id);
    List<Person> findAll();
    Person save(Person person);
    Person update(UUID id, Person person);
    void delete(UUID id);

    Person addEmail(UUID personId, EmailPerson email);
    Person addPhone(UUID personId, PhonePerson phone);

    Person updateEmail(UUID personId, UUID emailId, EmailPerson email);
    Person updatePhone(UUID personId, UUID phoneId, PhonePerson phone);

    Person removeEmail(UUID personId, UUID emailId);
    Person removePhone(UUID personId, UUID phoneId);
}

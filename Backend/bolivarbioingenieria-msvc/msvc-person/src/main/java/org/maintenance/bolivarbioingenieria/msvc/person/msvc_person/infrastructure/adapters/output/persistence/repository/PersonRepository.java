package org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.infrastructure.adapters.output.persistence.repository;

import org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.infrastructure.adapters.output.persistence.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {
}

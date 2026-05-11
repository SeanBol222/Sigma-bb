package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.repository;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Manufacturer;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities.ManufacturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringManufacturerRepository extends JpaRepository<ManufacturerEntity, String> {
}

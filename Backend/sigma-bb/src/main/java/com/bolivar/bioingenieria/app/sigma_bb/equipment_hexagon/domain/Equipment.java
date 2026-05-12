package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    private UUID id;
    private UUID equipmentTypeId;
    private UUID brandId;

    private EquipmentType equipmentType;
    private Brand brand;
}

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
public class Model {
    private UUID id;
    private String name;
    private UUID manufacturerId;
    private String equipmentId;
}

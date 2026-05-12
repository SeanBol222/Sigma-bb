package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetrologicalData {
    private BigDecimal value;
    private String type;
    private UUID equipmentTypeId;
}

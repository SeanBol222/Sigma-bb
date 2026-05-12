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
public class EquipmentType {
    private UUID id;
    private String equipmentTypeName;
    private String technicalDefinition;
    private String careRecommendations;
    private Integer voltage;
    private BigDecimal amperage;
    private String predominantTechnology;
    private Boolean verifiable;
    private Long unitMaintenanceValue;
}

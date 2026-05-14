package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentTypeRequest {
    @NotBlank(message = "El nombre del tipo de equipo es obligatorio")
    private String equipmentTypeName;

    @NotBlank(message = "La definición técnica es obligatoria")
    private String technicalDefinition;

    @NotBlank(message = "Las recomendaciones de cuidado son obligatorias")
    private String careRecommendations;

    @NotNull(message = "El voltage es obligatorio")
    private Integer voltage;

    @NotNull(message = "El amperaje es obligatorio")
    private BigDecimal amperage;

    @NotBlank(message = "La tecnología predominante es obligatoria")
    private String predominantTechnology;

    @NotNull(message = "El campo verificable es obligatorio")
    private Boolean verifiable;

    @NotNull(message = "El valor unitario de mantenimiento es obligatorio")
    private Long unitMaintenanceValue;

    private List<MetrologicalDataRequest> metrologicalData;
}

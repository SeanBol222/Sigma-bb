package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetrologicalDataRequest {
    @NotNull(message = "El valor es obligatorio")
    private BigDecimal value;

    @NotBlank(message = "El tipo es obligatorio")
    private String type;

    @NotBlank(message = "El id del tipo de equipo es obligatorio")
    private String equipmentTypeId;
}

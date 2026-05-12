package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelRequest {

    @NotBlank(message = "El invima del modelo es obligatorio")
    private String invima;

    @NotBlank(message = "El id del fabricante es obligatorio")
    private String manufacturerId;

    @NotBlank(message = "El id del equipo es obligatorio")
    private String equipmentId;
}

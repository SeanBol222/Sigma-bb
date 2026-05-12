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
public class ManufacturerRequest {
    @NotBlank(message = "El nombre del fabricante es obligatorio")
    private String name;

    @NotBlank(message = "El id del país es obligatorio")
    private String countryId;
}

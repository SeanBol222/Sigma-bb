package com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.infrastructure.input.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityRequest {
    @NotBlank(message = "El id de la ciudad es obligatorio")
    private String id;

    @NotBlank(message = "El nombre de la ciudad es obligatorio")
    private String name;

    @NotBlank(message = "El id del país es obligatorio")
    private String countryId;
}

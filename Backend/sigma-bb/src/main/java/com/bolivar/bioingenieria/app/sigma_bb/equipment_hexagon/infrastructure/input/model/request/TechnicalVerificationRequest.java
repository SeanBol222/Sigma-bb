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
public class TechnicalVerificationRequest {
    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @NotBlank(message = "El tipo de verificación es obligatorio")
    private String verificationType;
}

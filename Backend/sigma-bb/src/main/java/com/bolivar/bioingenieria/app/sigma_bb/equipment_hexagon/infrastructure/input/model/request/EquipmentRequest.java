package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentRequest {
    @NotBlank(message = "El id del tipo de equipo no puede estar vacio")
    private String equipmentTypeId;

    @NotBlank(message = "El id de la marca no puede estar vacio")
    private String brandId;
}

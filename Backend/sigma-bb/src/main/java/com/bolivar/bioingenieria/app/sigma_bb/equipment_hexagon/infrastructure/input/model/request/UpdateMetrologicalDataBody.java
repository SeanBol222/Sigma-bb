package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMetrologicalDataBody {

    @Valid
    @NotNull
    private MetrologicalDataRequest oldData;

    @Valid
    @NotNull
    private MetrologicalDataRequest newData;
}

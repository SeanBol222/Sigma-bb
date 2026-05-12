package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetrologicalDataId implements Serializable {

    @Column(name = "d_valor", precision = 10, scale = 4, nullable = false)
    private BigDecimal value;

    @Column(name = "n_tipo", length = 50, nullable = false)
    private String type;

    @Column(name = "k_id_tipo_equipo")
    private UUID equipmentTypeId;
}

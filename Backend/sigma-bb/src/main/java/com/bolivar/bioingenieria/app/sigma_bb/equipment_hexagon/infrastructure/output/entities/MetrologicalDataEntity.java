package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("deleted_at = false")
@Table(name = "dato_metrologico")
public class MetrologicalDataEntity {

    @EmbeddedId
    private MetrologicalDataId id;

    @Column(name = "deleted_at", nullable = false)
    private Boolean deleted = false;
}

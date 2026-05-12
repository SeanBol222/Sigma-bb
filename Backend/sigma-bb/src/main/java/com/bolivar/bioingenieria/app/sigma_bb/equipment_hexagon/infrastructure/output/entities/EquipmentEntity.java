package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("deleted_at = false")
@SQLDelete(sql = "UPDATE equipo SET deleted_at = true WHERE k_id_equipo = ?")
@Table(name = "equipo")
public class EquipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "k_id_equipo")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "k_id_tipo_equipo", nullable = false)
    private EquipmentTypeEntity equipmentType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "k_id_marca", nullable = false)
    private BrandEntity brand;

    @Column(name = "deleted_at", nullable = false)
    private Boolean deleted = false;
}

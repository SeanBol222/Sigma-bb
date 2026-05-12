package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("deleted_at = false")
@SQLDelete(sql = "UPDATE tipo_equipo SET deleted_at = true WHERE k_id_tipo_equipo = ?")
@Table(name = "tipo_equipo")
public class EquipmentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "k_id_tipo_equipo")
    private UUID id;

    @Column(name = "n_nombre_tipo_equipo", length = 50, nullable = false)
    private String equipmentTypeName;

    @Column(name = "t_definicion_tecnica", length = 250)
    private String technicalDefinition;

    @Column(name = "t_recomendaciones_cuidado", length = 250)
    private String careRecommendations;

    @Column(name = "i_voltage")
    private Integer voltage;

    @Column(name = "d_amperaje")
    private BigDecimal amperage;

    @Column(name = "t_tecnologia_predominante", length = 50)
    private String predominantTechnology;

    @Column(name = "b_verificable")
    private Boolean verifiable;

    @Column(name = "m_valor_unitario_mantenimiento")
    private Long unitMaintenanceValue;

    @Column(name = "deleted_at", nullable = false)
    private Boolean deleted = false;
}

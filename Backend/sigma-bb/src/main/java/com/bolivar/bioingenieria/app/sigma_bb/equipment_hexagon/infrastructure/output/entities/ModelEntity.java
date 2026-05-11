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
@SQLDelete(sql = "UPDATE modelo SET deleted_at = true WHERE k_id_modelo = ?")
@Table(name = "modelo")
public class ModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "k_id_modelo")
    private UUID id;

    @Column(name = "n_invmia", nullable = false)
    private String name;

    @Column(name = "k_id_fabricante")
    private UUID manufacturerId;

    @Column(name = "k_id_equipo")
    private String equipmentId;

    @Column(name = "deleted_at", nullable = false)
    private Boolean deleted = false;
}

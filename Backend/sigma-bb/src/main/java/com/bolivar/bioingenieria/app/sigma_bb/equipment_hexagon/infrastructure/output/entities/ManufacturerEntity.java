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
@SQLDelete(sql = "UPDATE fabricante SET deleted_at = true WHERE k_id_fabricante = ?")
@Table(name = "fabricante")
public class ManufacturerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "k_id_fabricante")
    private UUID id;

    @Column(name = "n_nombre_fabricante", nullable = false)
    private String name;

    @Column(name = "k_id_pais")
    private String countryId;

    @Column(name = "deleted_at", nullable = false)
    private Boolean deleted = false;
}

package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * Entidad JPA que representa el área de servicio dentro del sistema.
 * Esta entidad se mapea a la tabla "area_servicio" en la base de datos
 * y contiene información sobre el nombre del área, su estado activo
 * y la relación con los equipos asociados a esa área.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "area_servicio")
public class ServiceAreaEntity {

    /**
     * Identificador único del área de servicio.
     * Se utiliza como clave primaria en la base de datos.
     */
    @Id
    @Column(name = "k_id_area_servicio", nullable = false, unique = true)
    private UUID identificadorAreaServicio;

    /**
     * Nombre del área de servicio.
     * Es un campo obligatorio que no puede estar en blanco.
     */
    @Column(name = "n_nombre_area", nullable = false)
    @NotBlank
    private String nombreAreaServicio;

    /**
     * Indica si el área de servicio está activa en el sistema.
     * true: área activa; false: área inactiva o deshabilitada.
     */
    @Column(name = "b_estado_activo", nullable = false)
    private boolean estadoActivo = true;

    /**
     * Relación uno a uno con la entidad {@link ManagerEntity}.
     * Cada área de servicio tiene asignado un único encargado o responsable.
     * La eliminación en cascada asegura que al eliminar un área de servicio,
     * también se elimine el encargado relacionado.
     */
    @OneToMany(mappedBy = "serviceArea",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<ManagerEntity> managerList;

    /**
     * Identificador único de la sede a la que pertenece el área de servicio.
     * Define una relación muchos a uno (ManyToOne) con la entidad {@link HeadquarterEntity},
     * donde múltiples áreas de servicio pueden estar asociadas a una misma sede. El campo
     * k_id_sede en la tabla area_servicio actúa como clave foránea que referencia a la sede.
     */
    @Column(name = "k_id_sede", nullable = false)
    @NotNull
    private UUID identificadorSede;
}

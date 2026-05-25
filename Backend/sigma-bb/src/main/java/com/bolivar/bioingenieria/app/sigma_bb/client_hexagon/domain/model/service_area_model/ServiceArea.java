package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.headquarter_model;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.client_model.Manager;
import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * Modelo de dominio que representa un área de servicio dentro de una sede de cliente.
 * Contiene información sobre el área, sus equipos asociados y el encargado responsable.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceArea {

    /**
     * Identificador único del área de servicio.
     * Tipo: {@link UUID}
     */
    private UUID identificadorAreaServicio;

    /**
     * Nombre o denominación del área de servicio.
     */
    private String nombreAreaServicio;

    /**
     * Indica si el área de servicio está activa en el sistema.
     * true: área en operación; false: área inactiva o desactivada.
     */
    private boolean estadoActivo;

    /**
     * Identificador único de la sede a la que pertenece esta área de servicio.
     * Tipo: {@link UUID}
     */
    private UUID identificadorSede;

    /**
     * Encargado responsable del área de servicio.
     * Tipo: {@link Manager}
     */
    private List<Manager> managerList;

    // ----------------------------------------------------------------------
    // ------- Métodos para gestionar encargados del área de servicio  ------
    // ----------------------------------------------------------------------

    /**
     * Agrega un {@link Manager} a la lista de encargados responsables del área de servicio.
     *
     * @param manager Objeto {@link Manager} a agregar
     */
    public void addManager(Manager manager) {
        this.managerList.add(manager);
    }

    /**
     * Elimina (cambia su estadoActivo a false) un {@link Manager} de la lista de encargados del área de servicio.
     *
     * @param idManager {@link UUID} de {@link Manager} a eliminar
     */
    public void removeManager(UUID idManager) {
        this.managerList.stream()
                .filter(m -> m.getIdentificadorEncargado().equals(idManager))
                .findFirst()
                .ifPresent(m -> m.setEstadoActivo(false));
    }
}

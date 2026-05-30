package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.headquarter_model;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.manager_model.Manager;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

/**
 * Modelo de dominio que representa una sede o sucursal de un cliente.
 * Contiene la información de ubicación, identificadores de referencias
 * y la lista de áreas de servicio asociadas a la sede.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class Headquarter {
    /**
     * Identificador único de la sede.
     * Tipo: {@link UUID}
     */
    private UUID identificadorSede;

    /**
     * Nombre o denominación de la sede.
     */
    private String nombreSede;

    /**
     * Nombre de la calle en la dirección de la sede.
     */
    private String direccionCalleSede;

    /**
     * Nombre de la carrera en la dirección de la sede.
     */
    private String direccionCarreraSede;

    /**
     * Número de dirección de la sede.
     */
    private String direccionNumeroSede;

    /**
     * Indica si la sede está activa en el sistema.
     * true: sede en operación; false: sede inactiva o cerrada.
     */
    private boolean estadoActivo;

    /**
     * Identificador único de la ciudad donde se ubica la sede.
     * Tipo: {@link UUID}
     */
    private String identificadorCiudad;

    /**
     * Identificador único del cliente al que pertenece la sede.
     * Tipo: {@link UUID}
     */
    private String identificadorCliente;

    /**
     * Lista de encargados responsables de la sede.
     * Tipo: {@link List} de {@link Manager}
     */
    private List<Manager> managerList;

    // ----------------------------------------------------------------------
    // ----------- Métodos para gestionar encargados de la sede  ------------
    // ----------------------------------------------------------------------

    /**
    * Agrega un {@link Manager} a la lista de encargados de la sede.
    *
    * @param manager Objeto {@link Manager} a agregar
    */
    public Headquarter addManager(Manager manager) {
        this.managerList.add(manager);
        return this;
    }

    /**
     * Elimina (cambia su estadoActivo a false) un {@link Manager} de la lista de encargados de la sede.
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

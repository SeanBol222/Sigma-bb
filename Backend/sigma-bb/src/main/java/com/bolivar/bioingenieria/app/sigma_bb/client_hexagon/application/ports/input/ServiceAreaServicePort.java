package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.ports.input;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.client_equipment_model.ClientEquipment;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.manager_model.Manager;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.service_area_model.ServiceArea;

import java.util.List;
import java.util.UUID;

/**
 * Puerto de entrada que define las operaciones de negocio disponibles
 * para la gestión de {@link ServiceArea} y los equipos cliente asociados.
 *
 * <p>Esta interfaz establece el contrato que debe implementar el servicio
 * de aplicación para consultar, crear, actualizar y eliminar áreas de servicio,
 * así como administrar los equipos ({@link ClientEquipment}) y gerentes ({@link Manager})
 * vinculados a cada área.</p>
 */
public interface ServiceAreaServicePort {

    // ------------------------------------------------------------
    // ------------------ CRUD SERVICE AREA -----------------------
    // ------------------------------------------------------------

    /**
     * Busca una {@link ServiceArea} por su identificador único.
     *
     * @param serviceAreaId identificador de la {@link ServiceArea} a consultar
     * @return {@link ServiceArea} encontrada
     */
    ServiceArea findById(UUID serviceAreaId);

    /**
     * Obtiene todas las {@link ServiceArea} registradas.
     *
     * @return lista de {@link ServiceArea}
     */
    List<ServiceArea> findAll();

    /**
     * Crea y almacena una {@link ServiceArea}.
     *
     * @param serviceArea datos de la {@link ServiceArea} a persistir
     * @return {@link ServiceArea} almacenada
     */
    ServiceArea save(ServiceArea serviceArea);

    /**
     * Actualiza la información de una {@link ServiceArea} existente.
     *
     * @param serviceAreaId identificador de la {@link ServiceArea} a actualizar
     * @param serviceArea datos nuevos de la {@link ServiceArea}
     * @return {@link ServiceArea} actualizada
     */
    ServiceArea update(UUID serviceAreaId, ServiceArea serviceArea);

    /**
     * Elimina (marca como inactiva) una {@link ServiceArea} del sistema.
     *
     * @param serviceAreaId identificador de la {@link ServiceArea} a eliminar
     */
    void delete(UUID serviceAreaId);

    // ------------------------------------------------------------
    // ------------- Operaciones CRUD para Manager ----------------
    // ------------------------------------------------------------

    /**
     * Agrega un {@link Manager} a una {@link ServiceArea}.
     *
     * @param serviceAreaId identificador de la {@link ServiceArea}
     * @param manager datos del {@link Manager} a agregar
     * @return {@link ServiceArea} actualizada
     */
    ServiceArea addManger(UUID serviceAreaId, Manager manager);

    /**
     * Actualiza un {@link Manager} asociado a una {@link ServiceArea}.
     *
     * @param serviceAreaId identificador de la {@link ServiceArea}
     * @param managerId identificador del {@link Manager} a actualizar
     * @param manager datos nuevos del {@link Manager}
     * @return {@link ServiceArea} actualizada
     */
    ServiceArea updateManger(UUID serviceAreaId, UUID managerId, Manager manager);

    /**
     * Elimina (marca como inactivo) un {@link Manager} asociado a una {@link ServiceArea}.
     *
     * @param serviceAreaId identificador de la {@link ServiceArea}
     * @param managerId identificador del {@link Manager} a eliminar
     */
    ServiceArea deleteManger(UUID serviceAreaId, UUID managerId);

}

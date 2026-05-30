package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.ports.input;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.manager_use_case.ManagerUseCaseRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.service_area_use_case.ServiceAreaUseCaseRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.service_area_use_case.ServiceAreaUseCaseResponse;
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
     * Crea una nueva {@link ServiceArea} y la asocia a una {@link com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.headquarter_model.Headquarter}.
     *
     * @param headquarterId identificador de la {@link com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.headquarter_model.Headquarter} a la que se asociará el área de servicio
     * @param request datos de la nueva {@link ServiceArea} a crear
     * @return información de la {@link ServiceArea} creada
     */
    ServiceAreaUseCaseResponse save(UUID headquarterId, ServiceAreaUseCaseRequest request);

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
     * Agrega un nuevo {@link Manager} a una {@link ServiceArea}.
     *
     * @param serviceAreaId identificador de la {@link ServiceArea} a la que se asociará el nuevo {@link Manager}
     * @param request datos del nuevo {@link Manager} a agregar
     * @return {@link ServiceArea} actualizada con el nuevo {@link Manager}
     */
    ServiceArea addManger(UUID serviceAreaId, ManagerUseCaseRequest request);

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

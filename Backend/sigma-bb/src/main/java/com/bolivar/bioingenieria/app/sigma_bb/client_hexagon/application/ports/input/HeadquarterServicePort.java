package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.ports.input;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.headquarter_use_case.HeadquarterUseCaseRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.manager_use_case.ManagerUseCaseRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.headquarter_use_case.HeadquarterUseCaseResponse;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.headquarter_model.Headquarter;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.manager_model.Manager;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.service_area_model.ServiceArea;

import java.util.List;
import java.util.UUID;

/**
 * Puerto de entrada que define las operaciones de negocio disponibles
 * para la gestión de {@link Headquarter}.
 *
 * <p>Esta interfaz establece el contrato que debe implementar el servicio
 * de aplicación para consultar, crear, actualizar y eliminar sedes, así como
 * administrar sus {@link ServiceArea} asociadas.</p>
 */
public interface HeadquarterServicePort {

    // ------------------------------------------------------------
    // ------------ Operaciones CRUD para Headquarter -------------
    // ------------------------------------------------------------

    /**
     * Busca una {@link Headquarter} por su identificador único.
     *
     * @param headquarterId identificador de la {@link Headquarter} a consultar
     * @return {@link Headquarter} encontrada
     */
    Headquarter findByID(UUID headquarterId);

    /**
     * Obtiene todas las {@link Headquarter} registradas.
     *
     * @return lista de {@link Headquarter}
     */
    List<Headquarter> findAll();

    /**
     * Crea y almacena una {@link Headquarter}.
     *
     * @param headquarter datos de la {@link Headquarter} a persistir
     * @return {@link Headquarter} almacenada
     */
    HeadquarterUseCaseResponse save(String clientId, HeadquarterUseCaseRequest request);

    /**
     * Actualiza la información de una {@link Headquarter} existente.
     *
     * @param headquarterId identificador de la {@link Headquarter} a actualizar
     * @param headquarter datos nuevos de la {@link Headquarter}
     * @return {@link Headquarter} actualizada
     */
    Headquarter update(UUID headquarterId, Headquarter headquarter);

    /**
     * Elimina (marca como inactiva) una {@link Headquarter} del sistema.
     *
     * @param headquarterId identificador de la {@link Headquarter} a eliminar
     */
    void delete(UUID headquarterId);

    // ------------------------------------------------------------
    // ------------- Operaciones CRUD para Manager ---------------
    // ------------------------------------------------------------

    /**
     * Agrega un {@link Manager} a una {@link Headquarter}.
     *
     * @param headquarterId identificador de la {@link Headquarter}
     * @param manager datos del {@link Manager} a agregar
     * @return {@link Headquarter} actualizada
     */
    Headquarter addManger(UUID headquarterId, ManagerUseCaseRequest request);

    /**
     * Actualiza un {@link Manager} asociado a una {@link Headquarter}.
     *
     * @param headquarterId identificador de la {@link Headquarter}
     * @param managerId identificador del {@link Manager} a actualizar
     * @param manager datos nuevos del {@link Manager}
     * @return {@link Headquarter} actualizada
     */
    Headquarter updateManger(UUID headquarterId, UUID managerId, ManagerUseCaseRequest request);

    /**
     * Elimina (marca como inactivo) un {@link Manager} asociado a una {@link Headquarter}.
     *
     * @param headquarterId identificador de la {@link Headquarter}
     * @param managerId identificador del {@link Manager} a eliminar
     */
    Headquarter deleteManger(UUID headquarterId, UUID managerId);
}

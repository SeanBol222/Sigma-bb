package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.service;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.mapper.HeadquarterServiceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.mapper.ManagerServiceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.headquarter_use_case.HeadquarterUseCaseRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.manager_use_case.ManagerUseCaseRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.headquarter_use_case.HeadquarterUseCaseResponse;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.ports.input.HeadquarterServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.ports.output.ClientPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.ports.output.HeadquarterPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.exception.ClientNotFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.exception.HeadquarterFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.headquarter_model.Headquarter;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.manager_model.Manager;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.service_area_model.ServiceArea;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.ports.input.PersonCommunicationPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Implementación del servicio de aplicación para la gestión de {@link Headquarter}.
 * Coordina las operaciones de negocio entre los puertos de entrada
 * ({@link HeadquarterServicePort}) y los adaptadores de persistencia
 * ({@link HeadquarterPersistencePort}).
 *
 * <p>Se encarga de validar, transformar y orquestar las operaciones CRUD sobre sedes
 * ({@link Headquarter}) y sus entidades relacionadas: áreas de servicio ({@link ServiceArea})
 * y gerentes ({@link Manager}).</p>
 */
@Service
@AllArgsConstructor
public class HeadquarterService implements HeadquarterServicePort {

    private final HeadquarterPersistencePort headquarterPersistencePort; // Puerto de persistencia para acceder a los datos de sedes
    private final ClientPersistencePort clientPersistencePort; // Puerto de persistencia para acceder a los datos de equipos de cliente
    private final PersonCommunicationPort personCommunicationPort; // Puerto de comunicación para gestionar datos de personas (gerentes)
    private final ManagerServiceMapper managerServiceMapper; // Mapper para convertir entre DTOs de gerentes y el modelo de dominio
    private final HeadquarterServiceMapper headquarterServiceMapper; // Mapper para convertir entre DTOs de sedes y el modelo de dominio

    /**
     * Busca una {@link Headquarter} por su identificador único.
     *
     * @param headquarterId identificador de la {@link Headquarter} a consultar
     * @return {@link Headquarter} encontrada
     * @throws HeadquarterFoundException si la sede no existe
     */
    @Override
    public Headquarter findByID(UUID headquarterId) {
        //personComunicationPort.findById.map();
        return headquarterPersistencePort.findById(headquarterId)
                .orElseThrow(HeadquarterFoundException::new);
    }

    /**
     * Obtiene todas las {@link Headquarter} registradas.
     *
     * @return lista de {@link Headquarter}
     */
    @Override
    public List<Headquarter> findAll() {
        return headquarterPersistencePort.findAll();
    }

    /**
     * Crea y almacena una nueva {@link Headquarter}.
     * Genera identificadores únicos para la sede, el gerente responsable
     * y todas las {@link ServiceArea} asociadas.
     *
     * @param request datos de la {@link HeadquarterUseCaseRequest} a persistir
     * @return {@link Headquarter} almacenada
     */
    @Override
    @Transactional
    public HeadquarterUseCaseResponse save(
            String clientId,
            HeadquarterUseCaseRequest request
    ) {

        if (!clientPersistencePort.existsById(clientId)) {
            throw new ClientNotFoundException();
        }

        Headquarter headquarter = headquarterServiceMapper
                .toHeadquarter(request);

        headquarter.setIdentificadorSede(UUID.randomUUID());
        headquarter.setIdentificadorCliente(clientId);

        Manager manager = headquarter.getManagerList().getFirst();

        UUID managerId = addManagerLogicGetUUID(request.getManagerList().getFirst());

        manager.setIdentificadorEncargado(managerId);
        manager.setTipoEncargado("HEADQUARTER");

        Headquarter saved = headquarterPersistencePort.save(headquarter);

        return headquarterServiceMapper
                .toHeadquarterUseCaseResponse(saved);
    }

    /**
     * Actualiza la información de una {@link Headquarter} existente.
     *
     * @param headquarterId identificador de la {@link Headquarter} a actualizar
     * @param headquarter datos nuevos de la {@link Headquarter}
     * @return {@link Headquarter} actualizada
     * @throws HeadquarterFoundException si la sede no existe
     */
    @Override
    public Headquarter update(UUID headquarterId, Headquarter headquarter) {
        return headquarterPersistencePort.findById(headquarterId)
                .map(existingHeadquarter -> {
                    existingHeadquarter.setNombreSede(headquarter.getNombreSede());
                    existingHeadquarter.setDireccionCalleSede(headquarter.getDireccionCalleSede());
                    existingHeadquarter.setDireccionCarreraSede(headquarter.getDireccionCarreraSede());
                    existingHeadquarter.setDireccionNumeroSede(headquarter.getDireccionNumeroSede());
                    return headquarterPersistencePort.save(existingHeadquarter);
                })
                .orElseThrow(HeadquarterFoundException::new);
    }

    /**
     * Elimina (marca como inactiva) una {@link Headquarter} del sistema.
     *
     * @param headquarterId identificador de la {@link Headquarter} a eliminar
     * @throws HeadquarterFoundException si la sede no existe
     */
    @Override
    public void delete(UUID headquarterId) {
        headquarterPersistencePort.findById(headquarterId)
                .map(headquarter -> {
                    headquarter.setEstadoActivo(false);
                    return headquarterPersistencePort.save(headquarter);
                })
                .orElseThrow(HeadquarterFoundException::new);
    }

    // -----------------------------------------------------------
    // ------------- Operaciones CRUD para Manager ---------------
    // -----------------------------------------------------------

    /**
     * Agrega un {@link Manager} a una {@link Headquarter}.
     * Genera un identificador único para el nuevo gerente.
     *
     * @param headquarterId identificador de la {@link Headquarter}
     * @param request datos del {@link ManagerUseCaseRequest} a agregar
     * @return {@link Headquarter} actualizada
     * @throws HeadquarterFoundException si la sede no existe
     */
    @Override
    @Transactional
    public Headquarter addManger(UUID headquarterId, ManagerUseCaseRequest request) {
        return headquarterPersistencePort.findById(headquarterId)
                .map(existingHeadquarter -> {
                    existingHeadquarter.addManager(addManagerLogic(request));
                    return headquarterPersistencePort.save(existingHeadquarter);
                })
                .orElseThrow(HeadquarterFoundException::new);
    }

    /**
     * Actualiza un {@link Manager} asociado a una {@link Headquarter}.
     *
     * @param headquarterId identificador de la {@link Headquarter}
     * @param managerId identificador del {@link Manager} a actualizar
     * @param request datos nuevos del {@link ManagerUseCaseRequest}
     * @return {@link Headquarter} actualizada
     * @throws HeadquarterFoundException si la sede no existe
     */
    @Override
    public Headquarter updateManger(UUID headquarterId, UUID managerId, ManagerUseCaseRequest request) {
        return headquarterPersistencePort.findById(headquarterId)
                .map(existingHeadquarter -> {
                    existingHeadquarter.getManagerList().stream()
                            .filter(e -> e.getIdentificadorEncargado().equals(managerId))
                            .findFirst()
                            .ifPresent(e -> {
                                //e.setTipoEncargado(manager.getTipoEncargado());
                            });
                    return headquarterPersistencePort.save(existingHeadquarter);
                })
                .orElseThrow(HeadquarterFoundException::new);
    }

    /**
     * Elimina (marca como inactivo) un {@link Manager} asociado a una {@link Headquarter}.
     *
     * @param headquarterId identificador de la {@link Headquarter}
     * @param managerId identificador del {@link Manager} a eliminar
     * @throws HeadquarterFoundException si la sede no existe
     */
    @Override
    public Headquarter deleteManger(UUID headquarterId, UUID managerId) {
        return headquarterPersistencePort.findById(headquarterId)
                .map(existingHeadquarter -> {
                    existingHeadquarter.removeManager(managerId);
                    return headquarterPersistencePort.save(existingHeadquarter);
                })
                .orElseThrow(HeadquarterFoundException::new);
    }

    /**
     * Lógica auxiliar para agregar un {@link Manager} a una {@link Headquarter}.
     * Convierte el {@link ManagerUseCaseRequest} en un {@link Manager} y
     * persiste la información de la persona responsable a través del
     * {@link PersonCommunicationPort}.
     *
     * @param request datos del {@link ManagerUseCaseRequest} a agregar
     * @return {@link Manager} construido a partir del DTO y con el identificador de persona asignado
     */
    private UUID addManagerLogicGetUUID(ManagerUseCaseRequest request){
        return managerServiceMapper.toManager(request)
                .setIdentificadorEncargado(
                        personCommunicationPort.save(
                                managerServiceMapper.toPersonCommunicationRequest(request)
                                        .setTipoPersona("MANAGER")
                        ))
                .getIdentificadorEncargado();
    }

    /**
     * Lógica auxiliar para agregar un {@link Manager} a una {@link Headquarter}.
     * Convierte el {@link ManagerUseCaseRequest} en un {@link Manager} y
     * persiste la información de la persona responsable a través del
     * {@link PersonCommunicationPort}.
     *
     * @param request datos del {@link ManagerUseCaseRequest} a agregar
     * @return {@link Manager} construido a partir del DTO y con el identificador de persona asignado
     */
    private Manager addManagerLogic(ManagerUseCaseRequest request){
        return managerServiceMapper.toManager(request)
                .setIdentificadorEncargado(
                        personCommunicationPort.save(
                                managerServiceMapper.toPersonCommunicationRequest(request)
                                        .setTipoPersona("MANAGER")
                        )
                );
    }
}

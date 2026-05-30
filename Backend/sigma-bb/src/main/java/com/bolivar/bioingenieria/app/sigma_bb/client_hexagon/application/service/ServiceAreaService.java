package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.service;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.mapper.ManagerServiceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.mapper.ServiceAreaServiceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.manager_use_case.ManagerUseCaseRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.service_area_use_case.ServiceAreaUseCaseRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.service_area_use_case.ServiceAreaUseCaseResponse;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.ports.input.ServiceAreaServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.ports.output.HeadquarterPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.ports.output.ServiceAreaPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.exception.ClientNotFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.exception.HeadquarterFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.exception.ServiceAreaFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.client_equipment_model.ClientEquipment;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.headquarter_model.Headquarter;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.manager_model.Manager;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.service_area_model.ServiceArea;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.application.ports.input.PersonCommunicationPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Implementación del servicio de aplicación para la gestión de {@link ServiceArea}.
 * Coordina las operaciones de negocio entre los puertos de entrada
 * ({@link ServiceAreaServicePort}) y los adaptadores de persistencia
 * ({@link ServiceAreaPersistencePort}).
 *
 * <p>Se encarga de validar, transformar y orquestar las operaciones CRUD sobre áreas
 * de servicio y sus entidades relacionadas: equipos cliente ({@link ClientEquipment})
 * y gerentes ({@link Manager}).</p>
 */
@Service
@AllArgsConstructor
public class ServiceAreaService implements ServiceAreaServicePort {

    private final ServiceAreaPersistencePort serviceAreaPersistencePort; // Puerto de persistencia para acceder a los datos de áreas de servicio
    private final HeadquarterPersistencePort headquarterPersistencePort; // Puerto de persistencia para acceder a los datos de sedes
    private final ServiceAreaServiceMapper serviceAreaServiceMapper; // Mapper para convertir entre DTOs
    private final ManagerServiceMapper managerServiceMapper; // Mapper para convertir entre DTOs de manager
    private final PersonCommunicationPort personCommunicationPort; // Puerto de comunicación para gestionar personas en el

    /**
     * Busca una {@link ServiceArea} por su identificador único.
     *
     * @param serviceAreaId identificador de la {@link ServiceArea} a consultar
     * @return {@link ServiceArea} encontrada
     * @throws ServiceAreaFoundException si el área no existe
     */
    @Override
    public ServiceArea findById(UUID serviceAreaId) {
        return serviceAreaPersistencePort.findById(serviceAreaId)
                .orElseThrow(ServiceAreaFoundException::new);
    }

    /**
     * Obtiene todas las {@link ServiceArea} registradas.
     *
     * @return lista de {@link ServiceArea}
     */
    @Override
    public List<ServiceArea> findAll() {
        return serviceAreaPersistencePort.findAll();
    }

    /**
     * Crea una nueva {@link ServiceArea} y la asocia a una {@link Headquarter}.
     *
     * @param headquarterId identificador de la {@link Headquarter} a la que se asociará el área de servicio
     * @param request datos de la nueva {@link ServiceArea} a crear
     * @return información de la {@link ServiceArea} creada
     * @throws ClientNotFoundException si la sede no existe
     */
    @Override
    public ServiceAreaUseCaseResponse save(UUID headquarterId, ServiceAreaUseCaseRequest request) {

        if (!headquarterPersistencePort.existsById(headquarterId)) {
            throw new ClientNotFoundException();
        }

        ServiceArea serviceArea = serviceAreaServiceMapper
                .toServiceArea(request);

        serviceArea.setIdentificadorAreaServicio(UUID.randomUUID());
        serviceArea.setIdentificadorSede(headquarterId);
        serviceArea.setEstadoActivo(true);

        Manager manager = serviceArea.getManagerList().getFirst();

        manager.setIdentificadorEncargado(addManagerLogicGetUUID(request.getManagerList().getFirst()));
        manager.setTipoEncargado("SERVICE_AREA");
        manager.setEstadoActivo(true);

        ServiceAreaUseCaseResponse response = serviceAreaServiceMapper
                .toServiceAreaUseCaseResponse(serviceAreaPersistencePort.save(serviceArea));

        System.out.println("\n\n\n\n\n" + response + "\n\n\n\n\n");

        return response;


//        return serviceAreaServiceMapper
//                .toServiceAreaUseCaseResponse(serviceAreaPersistencePort.save(serviceArea));
//
    }

    /**
     * Actualiza la información de una {@link ServiceArea} existente.
     *
     * @param serviceAreaId identificador de la {@link ServiceArea} a actualizar
     * @param serviceArea datos nuevos de la {@link ServiceArea}
     * @return {@link ServiceArea} actualizada
     * @throws ServiceAreaFoundException si el área no existe
     */
    @Override
    public ServiceArea update(UUID serviceAreaId, ServiceArea serviceArea) {
        return serviceAreaPersistencePort.findById(serviceAreaId)
                .map(existingServiceArea -> {
                    existingServiceArea.setNombreAreaServicio(serviceArea.getNombreAreaServicio());
                    return serviceAreaPersistencePort.save(existingServiceArea);
                })
                .orElseThrow(ServiceAreaFoundException::new);
    }

    /**
     * Elimina (marca como inactiva) una {@link ServiceArea} del sistema.
     *
     * @param serviceAreaId identificador de la {@link ServiceArea} a eliminar
     * @throws ServiceAreaFoundException si el área no existe
     */
    @Override
    public void delete(UUID serviceAreaId) {
        serviceAreaPersistencePort.findById(serviceAreaId)
                .map(existingServiceArea -> {
                    existingServiceArea.setEstadoActivo(false);
                    return serviceAreaPersistencePort.save(existingServiceArea);
                }).orElseThrow(ServiceAreaFoundException::new);

    }

    // ------------------------------------------------------------
    // -----------------------CRUD MANAGER-------------------------
    // ------------------------------------------------------------

    /**
     * Agrega un nuevo {@link Manager} a una {@link ServiceArea}.
     *
     * @param serviceAreaId identificador de la {@link ServiceArea} a la que se asociará el nuevo {@link Manager}
     * @param request datos del nuevo {@link Manager} a agregar
     * @return {@link ServiceArea} actualizada con el nuevo {@link Manager}
     * @throws ServiceAreaFoundException si el área no existe
     */
    @Override
    public ServiceArea addManger(UUID serviceAreaId, ManagerUseCaseRequest request) {
        Manager newManager = managerServiceMapper.toManager(request);
        newManager.setIdentificadorEncargado(addManagerLogicGetUUID(request));
        newManager.setEstadoActivo(true);
        newManager.setTipoEncargado("SERVICE_AREA");

        return serviceAreaPersistencePort.findById(serviceAreaId)
                .map(existingServiceArea -> {
                    existingServiceArea.addManager(newManager);
                    return serviceAreaPersistencePort.save(existingServiceArea);
                })
                .orElseThrow(ServiceAreaFoundException::new);
    }

    /**
     * Actualiza un {@link Manager} asociado a una {@link ServiceArea}.
     *
     * @param serviceAreaId identificador de la {@link ServiceArea}
     * @param managerId identificador del {@link Manager} a actualizar
     * @param manager datos nuevos del {@link Manager}
     * @return {@link ServiceArea} actualizada
     * @throws ServiceAreaFoundException si el área no existe o el gerente no coincide
     */
    @Override
    public ServiceArea updateManger(UUID serviceAreaId, UUID managerId, Manager manager) {
        return serviceAreaPersistencePort.findById(serviceAreaId)
                .map(existingServiceArea -> {
                    existingServiceArea.getManagerList().stream()
                            .filter(m -> m.getIdentificadorEncargado().equals(managerId))
                            .findFirst()
                            .ifPresent(m -> {
                                m.setTipoEncargado(manager.getTipoEncargado());
                            });
                    return serviceAreaPersistencePort.save(existingServiceArea);
                }).orElseThrow(ServiceAreaFoundException::new);
    }

    /**
     * Elimina (marca como inactivo) un {@link Manager} asociado a una {@link ServiceArea}.
     *
     * @param serviceAreaId identificador de la {@link ServiceArea}
     * @param managerId identificador del {@link Manager} a eliminar
     * @throws ServiceAreaFoundException si el área no existe o el gerente no coincide
     */
    @Override
    public ServiceArea deleteManger(UUID serviceAreaId, UUID managerId) {
        return serviceAreaPersistencePort.findById(serviceAreaId)
                .map(existingServiceArea -> {
                    existingServiceArea.removeManager(managerId);
                    return serviceAreaPersistencePort.save(existingServiceArea);
                }).orElseThrow(ServiceAreaFoundException::new);
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
}

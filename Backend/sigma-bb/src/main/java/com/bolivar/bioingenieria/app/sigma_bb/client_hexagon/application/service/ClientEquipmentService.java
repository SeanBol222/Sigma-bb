package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.service;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.ports.input.ClientEquipmentServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.ports.output.ClientEquipmentPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.exception.ClientEquipmentFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.client_equipment_model.ClientEquipment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Servicio de aplicación para la gestión de equipos de clientes.
 * Implementa la lógica de negocio para operaciones CRUD sobre los equipos asociados a los clientes.
 */
@Service
@AllArgsConstructor
public class ClientEquipmentService implements ClientEquipmentServicePort
{
    private final ClientEquipmentPersistencePort clientEquipmentPersistencePort; // Puerto de persistencia para acceder a los datos de equipos de clientes

    /**
     * Busca un equipo de cliente por su identificador único.
     *
     * @param clientEquipmentId Identificador del equipo de cliente a buscar
     * @return El equipo de cliente encontrado
     * @throws ClientEquipmentFoundException Si no se encuentra el equipo de cliente con el ID proporcionado
     */
    @Override
    public ClientEquipment findByID(UUID clientEquipmentId) {
        return clientEquipmentPersistencePort.findByID(clientEquipmentId)
                .orElseThrow(ClientEquipmentFoundException::new);
    }

    /**
     * Busca todos los equipos de clientes registrados en el sistema.
     *
     * @return Lista de equipos de clientes encontrados
     */
    @Override
    public List<ClientEquipment> findAll() {
        return clientEquipmentPersistencePort.findAll();
    }

    /**
     * Guarda un nuevo equipo de cliente en el sistema.
     *
     * @param clientEquipment Objeto {@link ClientEquipment} a guardar
     * @return El equipo de cliente guardado con su identificador generado
     */
    @Override
    public ClientEquipment save(UUID serviceAreaId, UUID modelId, ClientEquipment clientEquipment) {

        clientEquipment.setIdentificadorEquipoCliente(UUID.randomUUID());
        clientEquipment.setEstadoActivo(true);
        clientEquipment.setIdentificadorAreaServicio(serviceAreaId);
        clientEquipment.setIdentificadorModelo(modelId);

        return clientEquipmentPersistencePort.save(clientEquipment);
    }

    /**
     * Actualiza un equipo de cliente existente con nuevos datos.
     *
     * @param clientEquipmentId Identificador del equipo de cliente a actualizar
     * @param clientEquipment   Objeto {@link ClientEquipment} con los datos actualizados
     * @return El equipo de cliente actualizado
     * @throws ClientEquipmentFoundException Si no se encuentra el equipo de cliente con el ID proporcionado
     */
    @Override
    public ClientEquipment update(UUID clientEquipmentId, ClientEquipment clientEquipment) {
        return clientEquipmentPersistencePort.findByID(clientEquipmentId)
                .map(existingEquipment -> {
                    existingEquipment.setSerie(clientEquipment.getSerie());
                    existingEquipment.setFechaCompra(clientEquipment.getFechaCompra());
                    existingEquipment.setValorCompra(clientEquipment.getValorCompra());
                    existingEquipment.setNumeroInventario(clientEquipment.getNumeroInventario());
                    return clientEquipmentPersistencePort.save(existingEquipment);
                })
                .orElseThrow(ClientEquipmentFoundException::new);
    }

    /**
     * Elimina (desactiva) un equipo de cliente existente en el sistema.
     *
     * @param clientEquipmentId Identificador del equipo de cliente a eliminar
     * @throws ClientEquipmentFoundException Si no se encuentra el equipo de cliente con el ID proporcionado
     */
    @Override
    public void delete(UUID clientEquipmentId) {
        clientEquipmentPersistencePort.findByID(clientEquipmentId)
                .map(clientEquipment -> {
                    clientEquipment.setEstadoActivo(false);
                    return clientEquipmentPersistencePort.save(clientEquipment);
                }).orElseThrow(ClientEquipmentFoundException::new);

    }
}

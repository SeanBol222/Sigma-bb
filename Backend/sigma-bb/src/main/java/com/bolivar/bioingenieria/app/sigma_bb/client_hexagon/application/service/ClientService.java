package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.service;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.ports.input.ClientServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.ports.output.ClientPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.exception.ClientNotFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.client_model.*;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.headquarter_model.Headquarter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Implementación del servicio de aplicación para la gestión de {@link Client}.
 * Coordina las operaciones de negocio entre los puertos de entrada
 * ({@link ClientServicePort}) y los adaptadores de persistencia
 * ({@link ClientPersistencePort}).
 *
 * <p>Se encarga de validar, transformar y orquestar las operaciones CRUD sobre clientes
 * y sus contactos asociados (correos y teléfonos), así como la gestión de sedes
 * ({@link Headquarter}) asociadas a un cliente.</p>
 */
@Service
@AllArgsConstructor
public class ClientService implements ClientServicePort {

    private final ClientPersistencePort clientPersistencePort; // Puerto de persistencia para acceder a los datos de clientes

    // ---------------------------------------------------------------
    // -------------------- METODOS DE CLIENTE -----------------------
    // ---------------------------------------------------------------

    /**
     * Busca un {@link Client} por su identificador único.
     *
     * @param clientId identificador del {@link Client} a consultar
     * @return {@link Client} encontrado
     * @throws ClientNotFoundException si el cliente no existe
     */
    @Override
    public Client findByID(String clientId) {
        return clientPersistencePort.findById(clientId)
                .orElseThrow(ClientNotFoundException::new);
    }

    /**
     * Obtiene todos los {@link Client} registrados.
     *
     * @return lista de {@link Client}
     */
    @Override
    public List<Client> findAll() {
        return clientPersistencePort.findAll();
    }

    /**
     * Crea y almacena un nuevo {@link Client}.
     * Genera identificadores únicos para los {@link EmailClient} y {@link PhoneClient} asociados.
     *
     * @param client {@link Client} a persistir
     * @return {@link Client} almacenado
     */
    @Override
    public Client save(Client client) {
        for(EmailClient email : client.getEmailClientList()){
            email.setIdentificadorCorreoCliente(UUID.randomUUID());
            email.setEstadoActivo(true);
        }
        for(PhoneClient phone : client.getPhoneClientList()){
            phone.setIdentificadorTelefonoCliente(UUID.randomUUID());
            phone.setEstadoActivo(true);
        }
        return clientPersistencePort.save(client.setEstadoActivo(true));
    }

    /**
     * Actualiza la información de un {@link Client} existente.
     *
     * @param clientId identificador del {@link Client} a actualizar
     * @param client datos nuevos del {@link Client}
     * @return {@link Client} actualizado
     * @throws ClientNotFoundException si el cliente no existe
     */
    @Override
    public Client update(String clientId, Client client) {
        return clientPersistencePort.findById(clientId)
                .map(existingClient -> {
                    existingClient.setTipoIdentificacion(client.getTipoIdentificacion());
                    existingClient.setRazonSocial(client.getRazonSocial());
                    existingClient.setEmailClientList(client.getEmailClientList());
                    existingClient.setPhoneClientList(client.getPhoneClientList());
                    return clientPersistencePort.save(existingClient);
                })
                .orElseThrow(ClientNotFoundException::new);
    }

    /**
     * Elimina un {@link Client} del sistema cambiando su estado a inactivo.
     *
     * @param clientId identificador del {@link Client} a eliminar
     * @throws ClientNotFoundException si el cliente no existe
     */
    @Override
    public void delete(String clientId) {
        clientPersistencePort.findById(clientId)
                .map(existingClient -> {
                    existingClient.setEstadoActivo(false);
                    return clientPersistencePort.save(existingClient);
                })
                .orElseThrow(ClientNotFoundException::new);
    }

    // ---------------------------------------------------------------------------------
    // ----------------------- Métodos CRUD de EmailClient --------------------------
    // ---------------------------------------------------------------------------------

    /**
     * Agrega un correo electrónico a un {@link Client}.
     *
     * @param clientId identificador del {@link Client}
     * @param email {@link EmailClient} a agregar
     * @return {@link Client} actualizado
     * @throws ClientNotFoundException si el cliente no existe
     */
    @Override
    public Client addEmail(String clientId, EmailClient email) {
        return clientPersistencePort.findById(clientId)
                .map(existingClient -> {
                    email.setIdentificadorCorreoCliente(UUID.randomUUID());
                    existingClient.addEmail(email);
                    return clientPersistencePort.save(existingClient);
                })
                .orElseThrow(ClientNotFoundException::new);
    }

    /**
     * Actualiza un correo electrónico asociado a un {@link Client}.
     *
     * @param clientId identificador del {@link Client}
     * @param emailId identificador del {@link EmailClient} a actualizar
     * @param email datos nuevos del {@link EmailClient}
     * @return {@link Client} actualizado
     * @throws ClientNotFoundException si el cliente no existe
     */
    @Override
    public Client updateEmail(String clientId, UUID emailId, EmailClient email) {
        return clientPersistencePort.findById(clientId)
                .map(existingClient -> {
                    existingClient.getEmailClientList().stream()
                            .filter(e -> e.getIdentificadorCorreoCliente().equals(emailId))
                            .findFirst()
                            .ifPresent(e -> {
                                e.setCorreoCliente(email.getCorreoCliente());
                            });
                    return clientPersistencePort.save(existingClient);
                })
                .orElseThrow(ClientNotFoundException::new);
    }

    /**
     * Elimina un correo electrónico asociado a un {@link Client}.
     *
     * @param clientId identificador del {@link Client}
     * @param emailId identificador del {@link EmailClient} a eliminar
     * @return {@link Client} actualizado
     * @throws ClientNotFoundException si el cliente no existe
     */
    @Override
    public Client removeEmail(String clientId, UUID emailId) {
        return clientPersistencePort.findById(clientId)
                .map(existingClient -> {
                    existingClient.removeEmail(emailId);
                    return clientPersistencePort.save(existingClient);
                })
                .orElseThrow(ClientNotFoundException::new);
    }

    // ---------------------------------------------------------------------------------
    // ----------------------- Métodos CRUD de PhoneClient ---------------------------
    // ---------------------------------------------------------------------------------

    /**
     * Agrega un teléfono a un {@link Client}.
     *
     * @param clientId identificador del {@link Client}
     * @param phone {@link PhoneClient} a agregar
     * @return {@link Client} actualizado
     * @throws ClientNotFoundException si el cliente no existe
     */
    @Override
    public Client addPhone(String clientId, PhoneClient phone) {
        return clientPersistencePort.findById(clientId)
                .map(existingClient -> {
                    phone.setIdentificadorTelefonoCliente(UUID.randomUUID());
                    existingClient.addPhone(phone);
                    return clientPersistencePort.save(existingClient);
                })
                .orElseThrow(ClientNotFoundException::new);
    }

    /**
     * Actualiza un teléfono asociado a un {@link Client}.
     *
     * @param clientId identificador del {@link Client}
     * @param phoneId identificador del {@link PhoneClient} a actualizar
     * @param phone datos nuevos del {@link PhoneClient}
     * @return {@link Client} actualizado
     * @throws ClientNotFoundException si el cliente no existe
     */
    @Override
    public Client updatePhone(String clientId, UUID phoneId, PhoneClient phone) {
        return clientPersistencePort.findById(clientId)
                .map(existingClient -> {
                    existingClient.getPhoneClientList().stream()
                            .filter(p -> p.getIdentificadorTelefonoCliente().equals(phoneId))
                            .findFirst()
                            .ifPresent(p -> {
                                p.setTelefonoCliente(phone.getTelefonoCliente());
                            });
                    return clientPersistencePort.save(existingClient);
                })
                .orElseThrow(ClientNotFoundException::new);
    }

    /**
     * Elimina un teléfono asociado a un {@link Client}.
     *
     * @param clientId identificador del {@link Client}
     * @param phoneId identificador del {@link PhoneClient} a eliminar
     * @return {@link Client} actualizado
     * @throws ClientNotFoundException si el cliente no existe
     */
    @Override
    public Client removePhone(String clientId, UUID phoneId) {
        return clientPersistencePort.findById(clientId)
                .map(existingClient -> {
                    existingClient.removePhone(phoneId);
                    return clientPersistencePort.save(existingClient);
                })
                .orElseThrow(ClientNotFoundException::new);
    }

}

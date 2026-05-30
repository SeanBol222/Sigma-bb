package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.output.persistence.adapters;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.ports.output.ServiceAreaPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.service_area_model.ServiceArea;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.output.persistence.mapper.ServiceAreaPersistenceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.output.persistence.repository.ServiceAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Adaptador de persistencia que implementa el puerto de salida {@link ServiceAreaPersistencePort}.
 *
 * Proporciona las operaciones de persistencia para la entidad ServiceArea, actuando como
 * intermediario entre la lógica de aplicación y el repositorio de datos. Utiliza
 * ServiceAreaPersistenceMapper para las conversiones entre modelos de dominio y entidades
 * de persistencia.
 */
@Component
@RequiredArgsConstructor
public class ServiceAreaPersistenceAdapter implements ServiceAreaPersistencePort {

    private final ServiceAreaRepository serviceAreaRepository; // Repositorio de datos para ServiceArea
    private final ServiceAreaPersistenceMapper serviceAreaPersistenceMapper; // Mapper para convertir entre modelos de dominio y entidades de persistencia

    /**
     * Busca un área de servicio por su identificador único.
     *
     * @param id Identificador único (UUID) del área de servicio a buscar
     * @return Optional conteniendo el área de servicio si existe, Empty en caso contrario
     */
    @Override
    public Optional<ServiceArea> findById(UUID id) {
        return serviceAreaRepository.findById(id)
                .map(serviceAreaPersistenceMapper::toServiceArea);
    }

    /**
     * Obtiene la lista completa de todas las áreas de servicio almacenadas en la base de datos.
     *
     * @return Lista de todos los modelos de dominio ServiceArea
     */
    @Override
    public List<ServiceArea> findAll() {
        return serviceAreaPersistenceMapper.toServiceAreaList(
                serviceAreaRepository.findAll());
    }

    /**
     * Persiste un nuevo área de servicio o actualiza una existente en la base de datos.
     *
     * @param serviceArea Modelo de dominio ServiceArea a persistir
     * @return Modelo de dominio ServiceArea con los datos persistidos
     */
    @Override
    public ServiceArea save(ServiceArea serviceArea) {
        return serviceAreaPersistenceMapper.toServiceArea(
                serviceAreaRepository.save(
                        serviceAreaPersistenceMapper.toServiceAreaEntity(serviceArea)));
    }

    /**
     * Elimina un área de servicio de la base de datos.
     *
     * @param serviceArea Modelo de dominio ServiceArea a eliminar
     */
    @Override
    public void delete(ServiceArea serviceArea) {
        serviceAreaPersistenceMapper.toServiceArea(
                serviceAreaRepository.save(
                        serviceAreaPersistenceMapper.toServiceAreaEntity(serviceArea)));
    }
}

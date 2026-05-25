package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.bootstrap.map_struct.BooleanMapper;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.manager_use_case.ManagerUseCaseRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.create_person.ManagerCreateCommunicationResponse;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.manager_use_case.ManagerUseCaseResponse;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.manager_model.Manager;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.model.request.PersonCommunicationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper para convertir entre {@link ManagerUseCaseRequest}, {@link ManagerUseCaseResponse} y el modelo de dominio {@link Manager}.
 */
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {BooleanMapper.class})
public interface ManagerServiceMapper {

    /**
     * Convierte un {@link ManagerUseCaseRequest} en un {@link Manager}.
     *
     * @param request DTO de entrada con los datos del manager
     * @return {@link Manager} construido a partir del DTO
     */
    Manager toManager(ManagerUseCaseRequest request);

    /**
     * Convierte un {@link Manager} en un {@link ManagerUseCaseResponse}.
     *
     * @param manager modelo de dominio a convertir
     * @return {@link ManagerUseCaseResponse} con la información del manager
     */
    ManagerUseCaseResponse toManagerDTOResponse(Manager manager);

    /**
     * Convierte un {@link ManagerUseCaseRequest} en un {@link ManagerCreateCommunicationResponse}.
     *
     * @param request DTO de entrada con los datos del manager
     * @return {@link ManagerCreateCommunicationResponse} construido a partir del DTO
     */
    PersonCommunicationRequest toPersonCommunicationRequest (ManagerUseCaseRequest request);

    /**
     * Convierte una lista de {@link Manager} en una lista de {@link ManagerUseCaseResponse}.
     *
     * @param managerList lista de modelos de dominio a convertir
     * @return lista de {@link ManagerUseCaseResponse} con la información de los managers
     */
    List<ManagerUseCaseResponse> toManagerDTOResponseList(List<Manager> managerList);

}

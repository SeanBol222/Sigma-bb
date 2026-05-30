package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.input.rest.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.manager_use_case.ManagerUseCaseRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.manager_model.Manager;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.input.rest.model.request.manager_request.ManagerCreateRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.input.rest.model.response.manager_response.ManagerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper REST para convertir entre {@link Manager}, {@link ManagerCreateRequest}
 * y {@link ManagerResponse}.
 *
 * <p>Este componente se encarga de transformar los DTO de entrada y salida
 * utilizados en la capa REST hacia y desde el modelo de dominio relacionado con los gerentes.</p>
 */
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ManagerRestMapper {

    /**
     * Convierte un {@link ManagerCreateRequest} en un {@link Manager}.
     *
     * @param managerCreateRequest DTO de entrada con los datos del gerente
     * @return {@link Manager} construido a partir del DTO
     */
    Manager toManager(ManagerCreateRequest managerCreateRequest);

    /**
     * Convierte un {@link ManagerCreateRequest} en un {@link ManagerUseCaseRequest}.
     *
     * @param managerCreateRequest DTO de entrada con los datos del gerente
     * @return {@link ManagerUseCaseRequest} construido a partir del DTO
     */
    ManagerUseCaseRequest toManagerUseCaseRequest(ManagerCreateRequest managerCreateRequest);

    /**
     * Convierte un {@link Manager} en un {@link ManagerResponse}.
     *
     * @param manager modelo de dominio a convertir
     * @return {@link ManagerResponse} con la información del gerente
     */
    ManagerResponse toManagerResponse(Manager manager);

    /**
     * Convierte una lista de {@link Manager} en una lista de {@link ManagerResponse}.
     *
     * @param managerList lista de modelos de dominio a convertir
     * @return lista de {@link ManagerResponse}
     */
    List<ManagerResponse> toManagerResponseList(List<Manager> managerList);


}

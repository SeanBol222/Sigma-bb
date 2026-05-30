package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.service_area_use_case.ServiceAreaUseCaseRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.service_area_use_case.ServiceAreaUseCaseResponse;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.service_area_model.ServiceArea;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper para convertir entre {@link ServiceAreaUseCaseRequest}, {@link ServiceAreaUseCaseResponse}
 * y el modelo de dominio {@link ServiceArea}.
 */
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {
                ManagerServiceMapper.class
        })
public interface ServiceAreaServiceMapper {

    /**
     * Convierte un {@link ServiceAreaUseCaseRequest} en un {@link ServiceArea}.
     *
     * @param serviceAreaUseCaseRequest DTO de entrada con los datos del área de servicio
     * @return {@link ServiceArea} construido a partir del DTO
     */
    ServiceArea toServiceArea (ServiceAreaUseCaseRequest serviceAreaUseCaseRequest);

    /**
     * Convierte un {@link ServiceArea} en un {@link ServiceAreaUseCaseResponse}.
     *
     * @param serviceArea modelo de dominio a convertir
     * @return {@link ServiceAreaUseCaseResponse} con la información del área de servicio
     */
    ServiceAreaUseCaseResponse toServiceAreaUseCaseResponse (ServiceArea serviceArea);

    /**
     * Convierte una lista de {@link ServiceArea} en una lista de {@link ServiceAreaUseCaseResponse}.
     *
     * @param serviceAreaList lista de modelos de dominio a convertir
     * @return lista de {@link ServiceAreaUseCaseResponse} con la información de las áreas de servicio
     */
    List<ServiceAreaUseCaseResponse> toServiceAreaUseCaseResponseList (List<ServiceArea> serviceAreaList);
}

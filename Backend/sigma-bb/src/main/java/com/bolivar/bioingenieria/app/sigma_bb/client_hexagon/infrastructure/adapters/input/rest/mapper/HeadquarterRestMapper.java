package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.input.rest.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.headquarter_use_case.HeadquarterUseCaseRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.headquarter_use_case.HeadquarterUseCaseResponse;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.headquarter_model.Headquarter;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.input.rest.model.request.headquarter_request.HeadquarterCreateRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.input.rest.model.response.headquarter_response.HeadquarterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper REST para convertir entre {@link Headquarter} y {@link HeadquarterResponse}.
 *
 * <p>Este componente se encarga de transformar los DTO de entrada y salida
 * utilizados en la capa REST hacia y desde el modelo de dominio relacionado con las sedes de los clientes.</p>
 */
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {ManagerRestMapper.class})
public interface HeadquarterRestMapper {

    /**
     * Convierte un {@link HeadquarterCreateRequest} en un {@link Headquarter}.
     *
     * @param headquarterCreateRequest DTO de entrada con los datos de la sede
     * @return {@link Headquarter} construido a partir del DTO
     */
    Headquarter toHeadquarter(HeadquarterCreateRequest headquarterCreateRequest);

    /**
     * Convierte un {@link HeadquarterCreateRequest} en un {@link HeadquarterUseCaseRequest}.
     *
     * @param headquarterCreateRequest DTO de entrada con los datos de la sede
     * @return {@link HeadquarterUseCaseRequest} construido a partir del DTO
     */
    HeadquarterUseCaseRequest toHeadquarterUseCaseRequest(HeadquarterCreateRequest headquarterCreateRequest);

    /**
     * Convierte un {@link Headquarter} en un {@link HeadquarterResponse}.
     *
     * @param headquarter modelo de dominio a convertir
     * @return {@link HeadquarterResponse} con la información de la sede
     */
    HeadquarterResponse toHeadquarterResponse(Headquarter headquarter);

    /**
     * Convierte un {@link HeadquarterUseCaseResponse} en un {@link HeadquarterResponse}.
     *
     * @param headquarterUseCaseResponse DTO de respuesta del caso de uso con los datos de la sede
     * @return {@link HeadquarterResponse} construido a partir del DTO de caso de uso
     */
    HeadquarterResponse toHeadquarterResponseFromUseCase(HeadquarterUseCaseResponse headquarterUseCaseResponse);

    /**
     * Convierte una lista de {@link Headquarter} en una lista de {@link HeadquarterResponse}.
     *
     * @param headquarterList lista de modelos de dominio a convertir
     * @return lista de {@link HeadquarterResponse}
     */
    List<HeadquarterResponse> toHeadquarterResponseList(List<Headquarter> headquarterList);
}

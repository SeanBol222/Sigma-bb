package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.bootstrap.map_struct.BooleanMapper;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.headquarter_use_case.HeadquarterUseCaseRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.headquarter_use_case.HeadquarterUseCaseResponse;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.headquarter_model.Headquarter;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper para convertir entre {@link HeadquarterUseCaseRequest}, {@link HeadquarterUseCaseResponse}
 * y el modelo de dominio {@link Headquarter}.
 */
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {
                BooleanMapper.class,
                ManagerServiceMapper.class
        })
public interface HeadquarterServiceMapper {

    /**
     * Convierte un {@link HeadquarterUseCaseRequest} en un {@link Headquarter}.
     *
     * @param headquarterUseCaseRequest DTO de entrada con los datos de la sede
     * @return {@link Headquarter} construido a partir del DTO
     */
    Headquarter toHeadquarter(HeadquarterUseCaseRequest headquarterUseCaseRequest);

    /**
     * Convierte un {@link Headquarter} en un {@link HeadquarterUseCaseResponse}.
     *
     * @param headquarter modelo de dominio a convertir
     * @return {@link HeadquarterUseCaseResponse} con la información de la sede
     */
    HeadquarterUseCaseResponse toHeadquarterUseCaseResponse (Headquarter headquarter);

    /**
     * Convierte una lista de {@link Headquarter} en una lista de {@link HeadquarterUseCaseResponse}.
     *
     * @param headquarterList lista de modelos de dominio a convertir
     * @return lista de {@link HeadquarterUseCaseResponse} con la información de las sedes
     */
    List<HeadquarterUseCaseResponse> toHeadquarterUseCaseResponseList(List<Headquarter> headquarterList);
}

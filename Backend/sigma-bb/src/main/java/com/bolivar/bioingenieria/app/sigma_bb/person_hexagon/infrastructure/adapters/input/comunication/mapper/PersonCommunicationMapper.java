package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.bootstrap.map_struct.BooleanMapper;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.model.request.PersonCommunicationRequest;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.model.respose.PersonCommunicationResponse;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.person_model.Person;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.request.PersonCreateRequest;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.model.response.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapeador REST para transformar información relacionada con personas.
 * Convierte entre los DTOs de entrada y salida de la capa REST y el modelo de dominio {@link Person}.
 */
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {
                BooleanMapper.class,
                EmailPersonCommunicationMapper.class,
                PhonePersonCommunicationMapper.class
        })
public interface PersonCommunicationMapper {

    /**
     * Convierte un DTO de {@link PersonCreateRequest} a su modelo de dominio correspondiente.
     *
     * @param request Datos de la persona recibidos desde la capa REST
     * @return Objeto de dominio {@link Person} listo para ser utilizado en la lógica de negocio
     */
    Person toPerson(PersonCommunicationRequest request);

    /**
     * Convierte un modelo de dominio {@link Person} a su representación de respuesta REST.
     *
     * @param person Objeto de dominio con la información de la persona
     * @return DTO de respuesta {@link PersonResponse} para exponer al cliente
     */
    PersonCommunicationResponse toPersonCommunicationResponse(Person person);

}

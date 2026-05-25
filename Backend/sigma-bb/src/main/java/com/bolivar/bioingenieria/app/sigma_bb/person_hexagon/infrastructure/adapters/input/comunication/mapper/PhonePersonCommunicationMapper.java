package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.bootstrap.map_struct.BooleanMapper;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.person_model.PhonePerson;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.model.request.PhonePersonCommunicationRequest;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.model.respose.PhonePersonCommunicationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapeador REST para transformar información relacionada con teléfonos de personas.
 * Convierte entre los DTOs de entrada y salida de la capa REST y el modelo de dominio {@link PhonePerson}.
 */
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {BooleanMapper.class})
public interface PhonePersonCommunicationMapper {

    /**
     * Convierte un DTO de {@link PhonePersonCommunicationRequest} a su modelo de dominio correspondiente.
     *
     * @param request Datos del teléfono recibidos desde la capa REST
     * @return Objeto de dominio {@link PhonePerson} listo para ser utilizado en la lógica de negocio
     */
    PhonePerson toPhonePerson(PhonePersonCommunicationRequest request);

    /**
     * Convierte un modelo de dominio {@link PhonePerson} a su representación de respuesta REST.
     *
     * @param phonePerson Objeto de dominio con la información del teléfono
     * @return DTO de respuesta {@link PhonePersonCommunicationResponse} para exponer al cliente
     */
    PhonePersonCommunicationResponse toPhonePersonCommunicationResponse(PhonePerson phonePerson);

    /**
     * Convierte una lista de modelos de dominio {@link PhonePerson} a una lista de respuestas REST.
     *
     * @param phonePersonList Lista de teléfonos en el modelo de dominio
     * @return Lista de DTOs de respuesta {@link PhonePersonCommunicationResponse}
     */
    List<PhonePersonCommunicationResponse> toPhonePersonCommunicationResponseList(List<PhonePerson> phonePersonList);
}

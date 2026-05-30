package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.person_model.EmailPerson;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.model.request.EmailPersonCommunicationRequest;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.comunication.model.respose.EmailPersonCommunicationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapeador REST para conversiones entre el modelo de dominio {@link EmailPerson}
 * y los objetos usados en la capa de entrada REST (requests/responses).
 *
 * Utiliza MapStruct para generar las implementaciones en tiempo de compilación.
 * La política {@code unmappedSourcePolicy = ReportingPolicy.IGNORE} evita errores
 * cuando hay campos en la fuente que no se mapean explícitamente.
 */
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmailPersonCommunicationMapper {

    /**
     * Convierte una petición de creación {@link EmailPersonCommunicationRequest} al
     * modelo de dominio {@link EmailPerson}.
     *
     * @param request Request recibido por la API que contiene los datos del correo
     * @return Instancia de {@link EmailPerson} con los datos mapeados
     */
    EmailPerson toEmailPerson(EmailPersonCommunicationRequest request);

    /**
     * Convierte el modelo de dominio {@link EmailPerson} a la respuesta REST
     * {@link EmailPersonCommunicationResponse} que será enviada al cliente.
     *
     * @param emailPerson Modelo de dominio con la información del correo
     * @return Objeto {@link EmailPersonCommunicationResponse} listo para serializar en la respuesta
     */
    EmailPersonCommunicationResponse toEmailPersonCommunicationResponse(EmailPerson emailPerson);

    /**
     * Convierte una lista de modelos de dominio {@link EmailPerson} a una lista
     * de objetos de respuesta {@link EmailPersonCommunicationResponse}.
     *
     * @param emailPersonList Lista de modelos de dominio a convertir
     * @return Lista de respuestas {@link EmailPersonCommunicationResponse}
     */
    List<EmailPersonCommunicationResponse> toEmailPersonComunicationResponseList(List<EmailPerson> emailPersonList);
}

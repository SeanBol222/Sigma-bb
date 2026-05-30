package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.service_area_use_case;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.manager_use_case.ManagerUseCaseRequest;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.manager_use_case.ManagerUseCaseResponse;
import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * DTO de entrada del caso de uso para crear o actualizar un área de servicio.
 * Contiene la información del área de servicio, su estado activo y los encargados asociados.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceAreaUseCaseResponse {

    /**
     * Identificador único del área de servicio.
     * Tipo: {@link UUID}
     */
    private UUID identificadorAreaServicio;

    /**
     * Nombre o denominación del área de servicio.
     */
    private String nombreAreaServicio;

    /**
     * Indica si el área de servicio está activa en el sistema.
     * true: área en operación; false: área inactiva o desactivada.
     */
    private boolean estadoActivo;

    /**
     * Lista de encargados asociados al área de servicio.
     * Tipo: {@link List} de {@link ManagerUseCaseRequest}.
     */
    private List<ManagerUseCaseResponse> managerList;

}

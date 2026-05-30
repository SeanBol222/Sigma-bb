package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.service_area_use_case;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.request.manager_use_case.ManagerUseCaseRequest;
import lombok.*;

import java.util.List;

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
public class ServiceAreaUseCaseRequest {

    /**
     * Nombre o denominación del área de servicio.
     */
    private String nombreAreaServicio;

    /**
     * Lista de encargados asociados al área de servicio.
     * Tipo: {@link List} de {@link ManagerUseCaseRequest}.
     */
    private List<ManagerUseCaseRequest> managerList;

}

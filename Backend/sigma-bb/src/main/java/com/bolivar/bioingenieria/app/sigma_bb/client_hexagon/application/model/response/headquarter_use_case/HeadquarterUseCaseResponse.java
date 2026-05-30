package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.headquarter_use_case;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.application.model.response.manager_use_case.ManagerUseCaseResponse;
import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * DTO de entrada del caso de uso para crear o actualizar una sede.
 * Contiene la información de ubicación de la sede, su ciudad y los encargados asociados.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HeadquarterUseCaseResponse {

    /**
     * Identificador único de la sede.
     */
    private UUID identificadorSede;

    /**
     * Nombre o denominación de la sede.
     */
    private String nombreSede;

    /**
     * Calle de la dirección de la sede.
     */
    private String direccionCalleSede;

    /**
     * Carrera de la dirección de la sede.
     */
    private String direccionCarreraSede;

    /**
     * Número o complemento de la dirección de la sede.
     */
    private String direccionNumeroSede;

    /**
     * Indica si la sede está activa en el sistema.
     * true: sede en operación; false: sede inactiva o cerrada.
     */
    private boolean estadoActivo;

    /**
     * Lista de encargados asociados a la sede.
     * Tipo: {@link List} de {@link ManagerUseCaseResponse}.
     */
    private List<ManagerUseCaseResponse> managerList;

    /**
     * Identificador único del cliente al que pertenece la sede.
     */
    private String identificadorCliente;

    /**
     * Identificador único de la ciudad donde se ubica la sede.
     */
    private String identificadorCiudad;
}

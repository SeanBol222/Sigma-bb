package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.utils;

import lombok.Getter;

/**
 * Enumerado que define un catálogo de errores para la aplicación.
 *
 * <p>Cada constante representa un error específico que puede ocurrir en el sistema,
 * con un código único y un mensaje descriptivo asociado.</p>
 */
@Getter
public enum ClientErrorCatalog {

    /**
     * Error cuando un Cliente no es encontrado en la base de datos.
     * Código: ERR_CLIENT_001
     */
    CLIENT_NOT_FOUND("ERR_CLIENT_001", "Client not found"),

    /**
     * Error desconocido que no corresponde a ninguno de los casos específicos
     * definidos en el catálogo.
     * Código: ERR_UNKNOW_999
     */
    UNKNOWN_ERROR("ERR_UNKNOW_999", "Unknown error"),

    /**
     * Error cuando una Sede no es encontrada en la base de datos.
     * Código: ERR_HEADQUARTER_001
     */
    HEADQUARTER_NOT_FOUND("ERR_HEADQUARTER_001", "Headquarter not found"),

    /**
     * Error cuando un Área de Servicio no es encontrada en la base de datos.
     * Código: ERR_SERVICE_AREA_001
     */
    SERVICE_AREA_NOT_FOUND("ERR_SERVICE_AREA_001", "Service area not found"),

    /**
     * Error cuando un Equipo de Cliente no es encontrado en la base de datos.
     * Código: ERR_CLIENT_EQUIPMENT_001
     */
    CLIENT_EQUIPMENT_NOT_FOUND("ERR_CLIENT_EQUIPMENT_001", "Client equipment not found");

    private final String code;
    private final String message;

    /**
     * Constructor del enumerado ErrorCatalog.
     *
     * @param code Código único del error para identificación
     * @param message Mensaje descriptivo del error en inglés
     */
    ClientErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

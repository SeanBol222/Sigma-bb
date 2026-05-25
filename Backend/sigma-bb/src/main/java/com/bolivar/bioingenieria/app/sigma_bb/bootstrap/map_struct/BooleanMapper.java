package com.bolivar.bioingenieria.app.sigma_bb.bootstrap.map_struct;

import org.mapstruct.Mapper;

/**
 * Mapper para convertir entre {@link Boolean} y {@code boolean}.
 *
 * <p>Este componente se encarga de manejar la conversión entre tipos booleanos primitivos y sus equivalentes envolventes, permitiendo que los valores nulos se interpreten como {@code true}.</p>
 */
@Mapper(componentModel = "spring")
public interface BooleanMapper {

    /**
     * Convierte un {@link Boolean} en un {@code boolean}.
     *
     * @param value valor booleano envolvente que puede ser nulo
     * @return {@code true} si el valor es nulo o {@code true}, de lo contrario {@code false}
     */
    default boolean fromNullable(Boolean value) {
        return value == null || value;
    }
}

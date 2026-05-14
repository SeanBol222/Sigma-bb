package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.metrological_data.events;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.MetrologicalData;
import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.DomainEvent;
import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.EventMetadata;

public record MetrologicalDataUpdatedEvent(EventMetadata metadata, MetrologicalData payload) implements DomainEvent<MetrologicalData> {}

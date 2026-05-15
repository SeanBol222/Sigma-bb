package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.model.events;

import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.DomainEvent;
import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.EventMetadata;

public record ModelDeletedEvent(EventMetadata metadata, ModelPayload payload) implements DomainEvent<ModelPayload> {}

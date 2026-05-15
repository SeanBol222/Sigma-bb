package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.brand.events;

import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.DomainEvent;
import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.EventMetadata;

public record BrandCreatedEvent(EventMetadata metadata, BrandPayload payload) implements DomainEvent<BrandPayload> {}

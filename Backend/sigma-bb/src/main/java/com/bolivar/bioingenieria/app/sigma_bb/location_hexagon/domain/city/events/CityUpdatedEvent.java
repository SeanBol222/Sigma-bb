package com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.domain.city.events;

import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.DomainEvent;
import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.EventMetadata;

public record CityUpdatedEvent(EventMetadata metadata, CityPayload payload) implements DomainEvent<CityPayload> {}

package com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.domain.country.events;

import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.DomainEvent;
import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.EventMetadata;

public record CountryCreatedEvent(EventMetadata metadata, CountryPayload payload) implements DomainEvent<CountryPayload> {}

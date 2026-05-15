package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.technical_verification.events;

import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.DomainEvent;
import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.EventMetadata;

public record TechnicalVerificationDeletedEvent(EventMetadata metadata, TechnicalVerificationPayload payload) implements DomainEvent<TechnicalVerificationPayload> {}

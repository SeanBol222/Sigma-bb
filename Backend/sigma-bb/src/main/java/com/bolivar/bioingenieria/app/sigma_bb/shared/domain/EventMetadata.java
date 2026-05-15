package com.bolivar.bioingenieria.app.sigma_bb.shared.domain;

import java.io.Serializable;
import java.time.Instant;

public record EventMetadata(
        String eventId,
        String eventType,
        int version,
        Instant occurredAt,
        String aggregateId
) implements Serializable {}

package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.equipment_type.events;

import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.DomainEvent;
import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.EventMetadata;

public record EquipmentTypeUpdatedEvent(EventMetadata metadata, EquipmentTypePayload payload) implements DomainEvent<EquipmentTypePayload> {}

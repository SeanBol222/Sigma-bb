package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.equipment.events;

import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.DomainEvent;
import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.EventMetadata;

public record EquipmentDeletedEvent(EventMetadata metadata, EquipmentPayload payload) implements DomainEvent<EquipmentPayload> {}

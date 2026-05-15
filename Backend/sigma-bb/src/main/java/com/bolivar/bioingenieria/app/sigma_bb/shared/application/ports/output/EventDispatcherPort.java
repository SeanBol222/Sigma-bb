package com.bolivar.bioingenieria.app.sigma_bb.shared.application.ports.output;

import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.DomainEvent;
import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.Payload;

public interface EventDispatcherPort {
    void dispatch(String nameEntity, String type, DomainEvent<? extends Payload> event);
}

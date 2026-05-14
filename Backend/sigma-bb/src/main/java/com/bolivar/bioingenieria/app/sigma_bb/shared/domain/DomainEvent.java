package com.bolivar.bioingenieria.app.sigma_bb.shared.domain;

import java.io.Serializable;

public interface DomainEvent<T extends Payload> extends Serializable {
    EventMetadata metadata();
    T payload();
}

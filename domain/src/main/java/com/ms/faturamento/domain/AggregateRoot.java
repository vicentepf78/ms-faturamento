package com.ms.faturamento.domain;

import com.ms.faturamento.domain.events.DomainEvent;

import java.util.List;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    protected AggregateRoot(final ID id) {
        super(id);
    }

    protected AggregateRoot(final ID id, final List<DomainEvent> events) {
        super(id, events);
    }
}

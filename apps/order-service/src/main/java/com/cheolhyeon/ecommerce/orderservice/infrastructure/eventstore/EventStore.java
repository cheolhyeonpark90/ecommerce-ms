package com.cheolhyeon.ecommerce.orderservice.infrastructure.eventstore;

import java.util.List;

public interface EventStore {
    void append(String aggregateId, Object event);
    List<Object> getEvents(String aggregateId);
}

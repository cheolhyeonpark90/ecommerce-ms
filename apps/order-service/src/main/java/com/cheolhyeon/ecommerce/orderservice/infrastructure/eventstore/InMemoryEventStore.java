package com.cheolhyeon.ecommerce.orderservice.infrastructure.eventstore;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryEventStore implements EventStore {

    private final Map<String, List<Object>> store = new HashMap<>();

    @Override
    public void append(String aggregateId, Object event) {
        store.computeIfAbsent(aggregateId, id -> new ArrayList<>()).add(event);
    }

    @Override
    public List<Object> getEvents(String aggregateId) {
        return store.getOrDefault(aggregateId, List.of());
    }
}

package com.cheolhyeon.ecommerce.orderservice.query;

import com.cheolhyeon.ecommerce.orderservice.domain.OrderStatus;
import com.cheolhyeon.ecommerce.orderservice.domain.event.OrderCreatedEvent;
import com.cheolhyeon.ecommerce.orderservice.domain.event.OrderStatusChangedEvent;
import com.cheolhyeon.ecommerce.orderservice.infrastructure.eventstore.EventStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderViewService {

    private final EventStore eventStore;

    public OrderView getById(String orderId) {
        var events = eventStore.getEvents(orderId);

        Long userId = null;
        OrderStatus status = null;

        for (Object event : events) {
            if (event instanceof OrderCreatedEvent created) {
                userId = created.userId();
                status = OrderStatus.REQUESTED;
            } else if (event instanceof OrderStatusChangedEvent changed) {
                status = changed.newStatus();
            }
        }

        if (userId == null || status == null) {
            throw new IllegalArgumentException("Order not found: " + orderId);
        }

        return OrderView.builder()
                .orderId(orderId)
                .userId(userId)
                .status(status)
                .build();
    }
}

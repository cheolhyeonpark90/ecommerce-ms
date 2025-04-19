package com.cheolhyeon.ecommerce.orderservice.service;

import com.cheolhyeon.ecommerce.orderservice.domain.Order;
import com.cheolhyeon.ecommerce.orderservice.domain.aggregate.OrderAggregate;
import com.cheolhyeon.ecommerce.orderservice.dto.OrderRequest;
import com.cheolhyeon.ecommerce.orderservice.infrastructure.eventstore.EventStore;
import com.cheolhyeon.ecommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final EventStore eventStore;

    public String create(OrderRequest request) {
        OrderAggregate aggregate = new OrderAggregate(request.userId());
        aggregate.getEvents().forEach(event -> {
            eventStore.append(aggregate.getOrderId(), event);
        });
        return aggregate.getOrderId();
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
    }
}

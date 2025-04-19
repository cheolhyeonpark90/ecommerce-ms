package com.cheolhyeon.ecommerce.orderservice.service;

import com.cheolhyeon.ecommerce.orderservice.domain.Order;
import com.cheolhyeon.ecommerce.orderservice.dto.OrderRequest;
import com.cheolhyeon.ecommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order create(OrderRequest request) {
        Order order = Order.create(request.userId());
        return orderRepository.save(order);
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
    }
}

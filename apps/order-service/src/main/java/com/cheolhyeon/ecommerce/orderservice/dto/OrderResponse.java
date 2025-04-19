package com.cheolhyeon.ecommerce.orderservice.dto;

import com.cheolhyeon.ecommerce.orderservice.domain.Order;
import com.cheolhyeon.ecommerce.orderservice.domain.OrderStatus;

public record OrderResponse(Long id, Long userId, OrderStatus status) {

    public static OrderResponse from(Order order) {
        return new OrderResponse(order.getId(), order.getUserId(), order.getStatus());
    }
}

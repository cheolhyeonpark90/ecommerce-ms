package com.cheolhyeon.ecommerce.orderservice.domain.event;

import com.cheolhyeon.ecommerce.orderservice.domain.OrderStatus;

public record OrderStatusChangedEvent(String orderId, OrderStatus newStatus) {}

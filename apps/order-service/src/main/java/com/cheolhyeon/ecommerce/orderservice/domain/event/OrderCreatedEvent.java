package com.cheolhyeon.ecommerce.orderservice.domain.event;

public record OrderCreatedEvent(String orderId, Long userId) {}

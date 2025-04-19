package com.cheolhyeon.ecommerce.orderservice.domain;

import java.util.Set;

public enum OrderStatus {

    REQUESTED,
    PENDING_APPROVAL,
    APPROVED,
    REJECTED,
    READY_TO_SHIP,
    SHIPPED,
    IN_TRANSIT,
    DELIVERED;

    public boolean canTransitionTo(OrderStatus next) {
        return switch (this) {
            case REQUESTED -> PENDING_APPROVAL == next;
            case PENDING_APPROVAL -> Set.of(APPROVED, REJECTED).contains(next);
            case APPROVED -> READY_TO_SHIP == next;
            case READY_TO_SHIP -> SHIPPED == next;
            case SHIPPED -> IN_TRANSIT == next;
            case IN_TRANSIT -> DELIVERED == next;
            default -> false;
        };
    }
}

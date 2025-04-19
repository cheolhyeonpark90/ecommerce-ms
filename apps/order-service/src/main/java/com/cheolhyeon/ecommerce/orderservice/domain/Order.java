package com.cheolhyeon.ecommerce.orderservice.domain;

import com.cheolhyeon.ecommerce.orderservice.exception.InvalidOrderStatusTransitionException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void changeStatus(OrderStatus nextStatus) {
        if (!this.status.canTransitionTo(nextStatus)) {
            throw new InvalidOrderStatusTransitionException(
                    String.format("Cannot change order status from %s to %s", this.status, nextStatus)
            );
        }
        this.status = nextStatus;
    }

    public static Order create(Long userId) {
        return Order.builder()
                .userId(userId)
                .status(OrderStatus.REQUESTED)
                .build();
    }
}

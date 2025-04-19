package com.cheolhyeon.ecommerce.orderservice.domain.aggregate;

import com.cheolhyeon.ecommerce.orderservice.domain.OrderStatus;
import com.cheolhyeon.ecommerce.orderservice.domain.event.OrderCreatedEvent;
import com.cheolhyeon.ecommerce.orderservice.domain.event.OrderStatusChangedEvent;
import com.cheolhyeon.ecommerce.orderservice.exception.InvalidOrderStatusTransitionException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class OrderAggregate {

    private final String orderId;
    private final Long userId;
    private OrderStatus status;

    // 이벤트 저장소 (추후 EventStore로 이동 가능)
    private final List<Object> events = new ArrayList<>();

    // 생성자 (주문 생성 이벤트 발생)
    public OrderAggregate(Long userId) {
        this.orderId = UUID.randomUUID().toString();
        this.userId = userId;
        this.status = OrderStatus.REQUESTED;

        // 이벤트 기록
        events.add(new OrderCreatedEvent(orderId, userId));
    }

    // 상태 전이
    public void transitionTo(OrderStatus nextStatus) {
        if (!status.canTransitionTo(nextStatus)) {
            throw new InvalidOrderStatusTransitionException(
                    String.format("Cannot change order status from %s to %s", status, nextStatus)
            );
        }

        status = nextStatus;
        events.add(new OrderStatusChangedEvent(orderId, status));
    }
}

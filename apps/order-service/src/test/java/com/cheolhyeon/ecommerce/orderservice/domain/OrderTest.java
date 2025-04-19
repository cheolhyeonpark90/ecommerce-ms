package com.cheolhyeon.ecommerce.orderservice.domain;

import com.cheolhyeon.ecommerce.orderservice.exception.InvalidOrderStatusTransitionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void 유효한_상태전이는_성공한다() {
        Order order = Order.create(1L);
        order.changeStatus(OrderStatus.PENDING_APPROVAL);
        assertEquals(OrderStatus.PENDING_APPROVAL, order.getStatus());
    }

    @Test
    void 유효하지_않은_상태전이는_예외를_던진다() {
        Order order = Order.create(1L);
        assertThrows(InvalidOrderStatusTransitionException.class, () ->
                order.changeStatus(OrderStatus.READY_TO_SHIP));
    }

    @Test
    void 상태가_REJECTED면_전이_불가능하다() {
        Order order = Order.builder().userId(1L).status(OrderStatus.REJECTED).build();
        assertThrows(InvalidOrderStatusTransitionException.class, () ->
                order.changeStatus(OrderStatus.APPROVED));
    }
}

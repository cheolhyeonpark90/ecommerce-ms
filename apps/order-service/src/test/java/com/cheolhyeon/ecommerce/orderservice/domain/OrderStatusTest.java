package com.cheolhyeon.ecommerce.orderservice.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderStatusTest {

    @Test
    void 요청됨에서_승인대기로_전이_성공() {
        assertTrue(OrderStatus.REQUESTED.canTransitionTo(OrderStatus.PENDING_APPROVAL));
    }

    @Test
    void 승인됨에서_출고대기로_전이_성공() {
        assertTrue(OrderStatus.APPROVED.canTransitionTo(OrderStatus.READY_TO_SHIP));
    }

    @Test
    void 요청됨에서_바로_출고대기는_불가() {
        assertFalse(OrderStatus.REQUESTED.canTransitionTo(OrderStatus.READY_TO_SHIP));
    }

    @Test
    void 배송완료된_주문은_다음_상태로_전이불가() {
        assertFalse(OrderStatus.DELIVERED.canTransitionTo(OrderStatus.REQUESTED));
    }

    @Test
    void 반려된_주문은_다음_상태로_전이불가() {
        assertFalse(OrderStatus.REJECTED.canTransitionTo(OrderStatus.APPROVED));
    }
}

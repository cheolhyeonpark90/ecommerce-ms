package com.cheolhyeon.ecommerce.orderservice.query;

import com.cheolhyeon.ecommerce.orderservice.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderView {
    private String orderId;
    private Long userId;
    private OrderStatus status;
}

package com.cheolhyeon.ecommerce.orderservice.controller;

import com.cheolhyeon.ecommerce.orderservice.dto.OrderRequest;
import com.cheolhyeon.ecommerce.orderservice.query.OrderView;
import com.cheolhyeon.ecommerce.orderservice.query.OrderViewService;
import com.cheolhyeon.ecommerce.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderViewService orderViewService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody OrderRequest request) {
        String orderId = orderService.create(request);
        return ResponseEntity.ok(orderId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderView> get(@PathVariable String id) {
        return ResponseEntity.ok(
                orderViewService.getById(id)
        );
    }
}

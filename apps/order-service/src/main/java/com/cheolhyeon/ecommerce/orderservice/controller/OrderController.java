package com.cheolhyeon.ecommerce.orderservice.controller;

import com.cheolhyeon.ecommerce.orderservice.dto.OrderRequest;
import com.cheolhyeon.ecommerce.orderservice.dto.OrderResponse;
import com.cheolhyeon.ecommerce.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(
                OrderResponse.from(orderService.create(request))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(
                OrderResponse.from(orderService.getById(id))
        );
    }
}

package com.cheolhyeon.ecommerce.orderservice.repository;

import com.cheolhyeon.ecommerce.orderservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

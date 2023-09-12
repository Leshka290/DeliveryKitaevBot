package com.tgproject.deliverykitaevbot.repository;

import com.tgproject.deliverykitaevbot.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

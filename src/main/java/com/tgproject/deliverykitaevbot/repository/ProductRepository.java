package com.tgproject.deliverykitaevbot.repository;


import com.tgproject.deliverykitaevbot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderId(Long id);
}

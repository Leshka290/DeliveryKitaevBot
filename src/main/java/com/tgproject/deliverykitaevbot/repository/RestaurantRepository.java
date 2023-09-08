package com.tgproject.deliverykitaevbot.repository;

import com.tgproject.deliverykitaevbot.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}

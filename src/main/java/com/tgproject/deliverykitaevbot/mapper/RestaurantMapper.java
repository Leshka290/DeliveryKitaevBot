package com.tgproject.deliverykitaevbot.mapper;

import com.tgproject.deliverykitaevbot.dto.RestaurantDto;
import com.tgproject.deliverykitaevbot.model.Restaurant;

public interface RestaurantMapper {

    RestaurantDto toDto(Restaurant restaurant);

    Restaurant toEntity(RestaurantDto dto);
}

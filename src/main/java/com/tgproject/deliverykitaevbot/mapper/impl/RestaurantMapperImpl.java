package com.tgproject.deliverykitaevbot.mapper.impl;

import com.tgproject.deliverykitaevbot.dto.RestaurantDto;
import com.tgproject.deliverykitaevbot.mapper.RestaurantMapper;
import com.tgproject.deliverykitaevbot.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RestaurantMapperImpl implements RestaurantMapper {

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public Restaurant toEntity(RestaurantDto dto) {
        return mapper.map(dto, Restaurant.class);
    }

    @Override
    public RestaurantDto toDto(Restaurant restaurant) {
        return mapper.map(restaurant, RestaurantDto.class);
    }
}

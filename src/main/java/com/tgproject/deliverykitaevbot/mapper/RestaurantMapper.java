package com.tgproject.deliverykitaevbot.mapper;

import com.tgproject.deliverykitaevbot.dto.RestaurantDto;
import com.tgproject.deliverykitaevbot.model.Restaurant;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantDto map(Restaurant restaurant);

    @InheritInverseConfiguration
    Restaurant map(RestaurantDto dto);
}

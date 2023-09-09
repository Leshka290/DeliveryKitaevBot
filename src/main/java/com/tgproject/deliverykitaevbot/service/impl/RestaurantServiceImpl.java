package com.tgproject.deliverykitaevbot.service.impl;

import com.tgproject.deliverykitaevbot.dto.RestaurantDto;
import com.tgproject.deliverykitaevbot.exception.RestaurantIsNotExistsException;
import com.tgproject.deliverykitaevbot.mapper.RestaurantMapper;
import com.tgproject.deliverykitaevbot.model.Restaurant;
import com.tgproject.deliverykitaevbot.repository.RestaurantRepository;
import com.tgproject.deliverykitaevbot.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public List<RestaurantDto> getAll() {
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public RestaurantDto create(RestaurantDto dto) {
        Restaurant restaurant = restaurantMapper.toEntity(dto);
        return restaurantMapper.toDto(restaurantRepository.save(restaurant));
    }

    @Override
    public RestaurantDto read(Long id) {
        return restaurantMapper.toDto(restaurantRepository.findById(id)
                .orElseThrow(RestaurantIsNotExistsException::new));
    }

    @Override
    public RestaurantDto update(Long id, RestaurantDto dto) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(RestaurantIsNotExistsException::new);
        restaurant.setId(id);
        restaurant.setName(dto.getName());
        return restaurantMapper.toDto(restaurantRepository.save(restaurant));
    }

    @Override
    public RestaurantDto delete(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(RestaurantIsNotExistsException::new);
        restaurantRepository.delete(restaurant);
        return restaurantMapper.toDto(restaurant);
    }
}

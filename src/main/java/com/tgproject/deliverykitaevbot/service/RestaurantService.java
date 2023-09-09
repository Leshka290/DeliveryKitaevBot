package com.tgproject.deliverykitaevbot.service;

import com.tgproject.deliverykitaevbot.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {

    /**
     * Получить все рестораны
     *
     * @return List<RestaurantDto>
     */
    List<RestaurantDto> getAll();

    /**
     * Создать новый ресторан
     *
     * @param dto RestaurantDto
     * @return RestaurantDto
     */
    RestaurantDto create(RestaurantDto dto);

    /**
     * Найти ресторан по ID
     *
     * @param id Long
     * @return RestaurantDto
     */
    RestaurantDto read(Long id);

    /**
     * Обновить ресторан
     *
     * @param id  Long
     * @param dto RestaurantDto
     * @return RestaurantDto
     */
    RestaurantDto update(Long id, RestaurantDto dto);

    /**
     * Удалить ресторан
     *
     * @param id Long
     * @return RestaurantDto
     */
    RestaurantDto delete(Long id);
}

package com.tgproject.deliverykitaevbot.service;

import com.tgproject.deliverykitaevbot.dto.OrderDto;
import com.tgproject.deliverykitaevbot.dto.UserCRUDDto;

import java.util.List;

public interface ProductService {

    /**
     * Получить все продукты
     *
     * @return List<OrderDto>
     */
    List<OrderDto> getAll();

    /**
     * Создать новый продукт
     *
     * @param orderDto OrderDto
     * @param userDto UserCRUDDto
     * @return OrderDto
     */
    OrderDto create(OrderDto orderDto, UserCRUDDto userDto);

    /**
     * Найти продукт по ID
     *
     * @param id Long
     * @return OrderDto
     */
    OrderDto read(Long id);

    /**
     * Обновить продукт
     *
     * @param id  Long
     * @param dto OrderDto
     * @return OrderDto
     */
    OrderDto update(Long id, OrderDto dto);

    /**
     * Удалить продукт
     *
     * @param id Long
     * @return OrderDto
     */
    OrderDto delete(Long id);
}

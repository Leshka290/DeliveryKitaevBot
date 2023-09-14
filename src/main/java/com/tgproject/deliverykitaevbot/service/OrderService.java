package com.tgproject.deliverykitaevbot.service;

import com.tgproject.deliverykitaevbot.dto.OrderDto;
import com.tgproject.deliverykitaevbot.dto.UserCRUDDto;

import java.util.List;

public interface OrderService {

    /**
     * Получить все заказы
     *
     * @return List<OrderDto>
     */
    List<OrderDto> getAll();

    /**
     * Создать новый заказ
     *
     * @param orderDto OrderDto
     * @param userDto UserCRUDDto
     * @return OrderDto
     */
    OrderDto create(OrderDto orderDto, UserCRUDDto userDto);

    /**
     * Найти заказ по Id
     *
     * @param id Long
     * @return OrderDto
     */
    OrderDto read(Long id);

    /**
     * Обновить заказ
     *
     * @param id  Long
     * @param dto OrderDto
     * @return OrderDto
     */
    OrderDto update(Long id, OrderDto dto);

    /**
     * Удалить заказ
     *
     * @param id Long
     * @return OrderDto
     */
    OrderDto delete(Long id);

    /**
     * Получить заказ с товарами по Id
     *
     * @param id Long
     * @return OrderDto
     */
    OrderDto findOrderAndProductsByOrderId(String id);
}

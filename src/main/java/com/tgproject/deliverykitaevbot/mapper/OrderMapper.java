package com.tgproject.deliverykitaevbot.mapper;

import com.tgproject.deliverykitaevbot.dto.OrderDto;
import com.tgproject.deliverykitaevbot.model.Order;

public interface OrderMapper {

    OrderDto toDto(Order order);

    Order toEntity(OrderDto orderDto);
}

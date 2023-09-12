package com.tgproject.deliverykitaevbot.mapper.impl;

import com.tgproject.deliverykitaevbot.dto.OrderDto;
import com.tgproject.deliverykitaevbot.mapper.OrderMapper;
import com.tgproject.deliverykitaevbot.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderMapperImpl implements OrderMapper {

    private final ModelMapper mapper = new ModelMapper();
    @Override
    public OrderDto toDto(Order order) {
        return mapper.map(order, OrderDto.class);
    }

    @Override
    public Order toEntity(OrderDto orderDto) {
        return mapper.map(orderDto, Order.class);
    }
}

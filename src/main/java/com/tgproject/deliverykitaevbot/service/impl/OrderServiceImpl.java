package com.tgproject.deliverykitaevbot.service.impl;

import com.tgproject.deliverykitaevbot.dto.OrderDto;
import com.tgproject.deliverykitaevbot.dto.UserCRUDDto;
import com.tgproject.deliverykitaevbot.exception.OrderIsNotExistsException;
import com.tgproject.deliverykitaevbot.mapper.OrderMapper;
import com.tgproject.deliverykitaevbot.model.Order;
import com.tgproject.deliverykitaevbot.repository.OrderRepository;
import com.tgproject.deliverykitaevbot.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public OrderDto create(OrderDto orderDto, UserCRUDDto userDto) {
        Order order = orderMapper.toEntity(orderDto);
        order.setAddress(orderDto.getAddress());
        order.setOrderId(RandomStringUtils.randomAlphanumeric(8));
        order.setUserId(userDto.getChatId());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public OrderDto read(Long id) {
        return orderMapper.toDto(orderRepository.findById(id)
                .orElseThrow(OrderIsNotExistsException::new));
    }

    @Override
    public OrderDto update(Long id, OrderDto dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(OrderIsNotExistsException::new);
        order.setId(id);
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public OrderDto delete(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(OrderIsNotExistsException::new);
        orderRepository.delete(order);
        return orderMapper.toDto(order);
    }
}

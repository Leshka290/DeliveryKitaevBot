package com.tgproject.deliverykitaevbot.service.impl;

import com.tgproject.deliverykitaevbot.dto.OrderDto;
import com.tgproject.deliverykitaevbot.dto.UserCRUDDto;
import com.tgproject.deliverykitaevbot.exception.OrderIsNotExistsException;
import com.tgproject.deliverykitaevbot.mapper.OrderMapper;
import com.tgproject.deliverykitaevbot.model.Order;
import com.tgproject.deliverykitaevbot.model.Product;
import com.tgproject.deliverykitaevbot.repository.OrderRepository;
import com.tgproject.deliverykitaevbot.repository.ProductRepository;
import com.tgproject.deliverykitaevbot.repository.UserRepository;
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
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto create(OrderDto orderDto, UserCRUDDto userDto) {
        Order order = orderMapper.map(orderDto);
        order.setAddress(orderDto.getAddress());
        order.setOrderId(RandomStringUtils.randomAlphanumeric(8));
        order.setUserId(userDto.getChatId());
        return orderMapper.map(orderRepository.save(order));
    }

    @Override
    public OrderDto read(Long id) {
         return orderMapper.map(orderRepository.findById(id).orElseThrow());
    }

    @Override
    public OrderDto update(Long id, OrderDto dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(OrderIsNotExistsException::new);
        order.setId(id);
        return orderMapper.map(orderRepository.save(order));
    }

    @Override
    public OrderDto delete(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(OrderIsNotExistsException::new);
        orderRepository.delete(order);
        return orderMapper.map(order);
    }

    @Override
    public OrderDto findOrderAndProductsByOrderId(String id) {
        List<Product> products = productRepository.findAll()
                .stream().filter(p -> p.getOrder().getOrderId().equals(id))
                .collect(Collectors.toList());
        Order order = orderRepository.findAll().stream()
                .filter(o -> o.getOrderId().equals(id)).findFirst()
                .orElseThrow(OrderIsNotExistsException::new);
        order.setProducts(products);
        return orderMapper.map(order);
    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::map)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

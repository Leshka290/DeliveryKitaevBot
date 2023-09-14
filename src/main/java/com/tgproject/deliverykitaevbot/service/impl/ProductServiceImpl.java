package com.tgproject.deliverykitaevbot.service.impl;

import com.tgproject.deliverykitaevbot.dto.OrderDto;
import com.tgproject.deliverykitaevbot.dto.UserCRUDDto;
import com.tgproject.deliverykitaevbot.mapper.ProductMapper;
import com.tgproject.deliverykitaevbot.repository.ProductRepository;
import com.tgproject.deliverykitaevbot.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<OrderDto> getAll() {
        return null;
    }

    @Override
    public OrderDto create(OrderDto orderDto, UserCRUDDto userDto) {
        return null;
    }

    @Override
    public OrderDto read(Long id) {
        return null;
    }

    @Override
    public OrderDto update(Long id, OrderDto dto) {
        return null;
    }

    @Override
    public OrderDto delete(Long id) {
        return null;
    }
}

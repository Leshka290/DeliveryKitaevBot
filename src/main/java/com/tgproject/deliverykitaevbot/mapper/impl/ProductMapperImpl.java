package com.tgproject.deliverykitaevbot.mapper.impl;

import com.tgproject.deliverykitaevbot.dto.ProductDto;
import com.tgproject.deliverykitaevbot.mapper.ProductMapper;
import com.tgproject.deliverykitaevbot.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductMapperImpl implements ProductMapper {

    private final ModelMapper mapper = new ModelMapper();
    @Override
    public Product toEntity(ProductDto dto) {
        return mapper.map(dto, Product.class);
    }

    @Override
    public ProductDto toDto(Product product) {
        return mapper.map(product, ProductDto.class);
    }
}

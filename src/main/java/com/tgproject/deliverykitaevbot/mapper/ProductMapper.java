package com.tgproject.deliverykitaevbot.mapper;

import com.tgproject.deliverykitaevbot.dto.ProductDto;
import com.tgproject.deliverykitaevbot.model.Product;

public interface ProductMapper {

    Product toEntity(ProductDto dto);

    ProductDto toDto(Product product);
}

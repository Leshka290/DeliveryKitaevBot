package com.tgproject.deliverykitaevbot.mapper;

import com.tgproject.deliverykitaevbot.dto.ProductDto;
import com.tgproject.deliverykitaevbot.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto map(Product product);

    @InheritInverseConfiguration
    Product map(ProductDto dto);
}

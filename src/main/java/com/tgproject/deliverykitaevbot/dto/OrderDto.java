package com.tgproject.deliverykitaevbot.dto;

import com.tgproject.deliverykitaevbot.model.Address;
import com.tgproject.deliverykitaevbot.model.Product;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
public class OrderDto implements Serializable {

    private Long id;
    private Long userId;
    private Long orderId;
    private Address address;
    private List<ProductDto> products;
}

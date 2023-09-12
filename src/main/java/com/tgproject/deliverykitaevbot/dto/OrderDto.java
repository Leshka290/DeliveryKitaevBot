package com.tgproject.deliverykitaevbot.dto;

import com.tgproject.deliverykitaevbot.model.Address;
import com.tgproject.deliverykitaevbot.model.Product;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

@Data
public class OrderDto implements Serializable {

    private Long id;
    private Long userId;
    private Long orderId;
    private Collection<Product> products;
    private Address address;
}

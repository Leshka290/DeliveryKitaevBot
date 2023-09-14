package com.tgproject.deliverykitaevbot.dto;

import com.tgproject.deliverykitaevbot.model.Image;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {

    private Long id;
    private int price;
    private String productInfo;
    private String name;
    private Image imageId;
    private OrderDto order;
}

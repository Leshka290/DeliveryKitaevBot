package com.tgproject.deliverykitaevbot.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Класс продукта для БД со свойствами:
 * <p>id</p><p>price</p><p>productInfo</p><p>name</p><p>imageId</p><p>order</p>
 *
 */
@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private int price;
    private String productInfo;
    private String name;
    @OneToOne()
    @JoinColumn(name = "image_id")
    private Image imageId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}

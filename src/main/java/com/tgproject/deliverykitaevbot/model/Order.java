package com.tgproject.deliverykitaevbot.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Класс заказ для БД со свойствами:
 * <p>id</p><p>userId</p><p>orderId</p><p>address</p>
 *
 */
@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String orderId;
    @OneToOne
    private Address address;
}

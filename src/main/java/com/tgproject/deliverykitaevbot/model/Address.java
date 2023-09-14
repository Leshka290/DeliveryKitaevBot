package com.tgproject.deliverykitaevbot.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Класс адресс доставки пользователя для БД со свойствами:
 * <p>id</p><p>street</p><p>house</p><p>apartment</p><p>userId</p>
 *
 */
@Entity
@Data
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private String house;
    private String apartment;
    @JoinColumn(name = "chat_id")
    @ManyToOne
    private User chatId;
}

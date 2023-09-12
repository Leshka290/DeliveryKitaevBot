package com.tgproject.deliverykitaevbot.model;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * Класс пользователь со свойствами:
 * <p>
 * <b>id</b>,<b>name</b>,<b>chatId</b>,<b>restaurantId</b>,<b>lastResponseStateMenuId</b>,
 * <b>phone</b>,<b>stateId</b>,<b>dtCreate</b>
 */
@Data
@Table(name="users")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long chatId;
    @Column(name = "restaurant_id")
    private Long restaurantId;
    private String phone;
    private Long lastResponseStateMenuId;
    @JoinColumn(name = "state_id")
    @OneToOne
    private UserState stateId;
    private OffsetDateTime dtCreate;

}

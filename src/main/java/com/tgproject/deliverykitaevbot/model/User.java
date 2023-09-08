package com.tgproject.deliverykitaevbot.model;

import com.tgproject.deliverykitaevbot.model.constant.TypeRestaurant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * Класс пользователь со свойствами:
 * <p>
 * <b>id</b>,<b>firstName</b>,<b>lastTypeRestaurant</b>,<b>chatId</b>
 */
@Data
@RequiredArgsConstructor
@Table(name="users")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private Long chatId;

    private TypeRestaurant lastTypeRestaurant;

    private Long restaurantId;

    private String phone;

    private Long lastResponseStateMenuId;

    @JoinColumn(name = "state_id")
    @OneToOne
    private UserState stateId;

    private OffsetDateTime dtCreate;
}

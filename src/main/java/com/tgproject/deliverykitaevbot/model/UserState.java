package com.tgproject.deliverykitaevbot.model;


import com.tgproject.deliverykitaevbot.model.constant.UserStateSpecial;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * Класс состояние пользователя со свойствами:
 * <p>
 * <b>id</b>,<b>firstName</b>,<b>tagSpecial</b>,<b>restaurantId</b>
 */
@Data
@Table(name = "user_state")
@Entity
public class UserState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private UserStateSpecial tagSpecial;
    @Column(name = "restaurant_id")
    private Long restaurantId;
}

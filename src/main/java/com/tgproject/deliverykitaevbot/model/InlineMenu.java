package com.tgproject.deliverykitaevbot.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * Класс состояние пользователя со свойствами:
 * <p>
 * <b>id</b>,<b>restaurantId</b>,<b>tagCallback</b>,<b>question</b>,<b>answer</b>
 */
@Data
@RequiredArgsConstructor
@Table(name = "inline_menu")
@Entity
public class InlineMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long restaurantId;
    private String tagCallback;
    private String question;
    private String answer;
    private String button;
    @OneToOne
    private UserState stateId;
    @OneToOne
    private UserState stateIdNext;
    private Integer priority;

}

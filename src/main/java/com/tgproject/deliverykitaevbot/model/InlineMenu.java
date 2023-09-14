package com.tgproject.deliverykitaevbot.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * Класс меню со свойствами:
 * <p>
 * <b>id</b>,<b>restaurantId</b>,<b>tagCallback</b>,<b>question</b>,<b>answer</b>
 */
@Data
@Table(name = "inline_menu")
@Entity
public class InlineMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id")
    private Long restaurantId;
    private String tagCallback;
    private String question;
    private String answer;
    private String button;
    @OneToOne
    @JoinColumn(name = "state_id")
    private UserState stateId;
    @JoinColumn(name = "state_id_next")
    @OneToOne
    private UserState stateIdNext;
    private Integer priority;

}

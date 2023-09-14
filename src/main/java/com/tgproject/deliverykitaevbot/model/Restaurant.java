package com.tgproject.deliverykitaevbot.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Класс ресторан со свойствами:
 * <p>
 * <b>id</b>,<b>name</b>
 */
@Data
@Table(name = "restaurants")
@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}

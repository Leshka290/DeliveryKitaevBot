package com.tgproject.deliverykitaevbot.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@RequiredArgsConstructor
@Table(name = "restaurants")
@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}

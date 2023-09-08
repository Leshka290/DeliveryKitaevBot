package com.tgproject.deliverykitaevbot.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

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

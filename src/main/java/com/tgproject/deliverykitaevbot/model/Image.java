package com.tgproject.deliverykitaevbot.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Класс изображения продукта для БД со свойствами:
 * <p>id</p><p>image</p>
 *
 */
@Entity
@Data
public class Image {

    @Id
    @GeneratedValue
    private Long id;
    private byte[] image;
}

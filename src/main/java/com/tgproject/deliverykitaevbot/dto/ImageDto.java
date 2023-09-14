package com.tgproject.deliverykitaevbot.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImageDto implements Serializable {

    private Long id;
    private byte[] image;
}

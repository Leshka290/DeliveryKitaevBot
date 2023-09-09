package com.tgproject.deliverykitaevbot.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
public class UserCRUDDto implements Serializable {

    private Long id;
    private Long chatId;
    private Long restaurantId;
    private String phone;
    private String name;
    private String info;
    private OffsetDateTime dtCreate;
}

package com.tgproject.deliverykitaevbot.dto;

import com.tgproject.deliverykitaevbot.model.User;
import lombok.Data;

@Data
public class AddressDto {

    private Long id;
    private String street;
    private String house;
    private String apartment;
    private User userId;
}

package com.tgproject.deliverykitaevbot.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    @NotNull
    private Long chatId;
    private Long restaurantId;
    private UserStateDto userStateDto;
}

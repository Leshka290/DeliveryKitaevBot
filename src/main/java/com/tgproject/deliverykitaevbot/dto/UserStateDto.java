package com.tgproject.deliverykitaevbot.dto;

import com.tgproject.deliverykitaevbot.model.constant.UserStateSpecial;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserStateDto implements Serializable {

    private Long id;
    @NotNull
    @NotBlank
    private String name;
    private UserStateSpecial tagSpecial;
    @NotNull
    private Long restaurantId;
}

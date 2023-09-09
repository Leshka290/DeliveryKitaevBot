package com.tgproject.deliverykitaevbot.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class RestaurantDto implements Serializable {

    private final Long id;
    @NotEmpty
    private final String name;
}

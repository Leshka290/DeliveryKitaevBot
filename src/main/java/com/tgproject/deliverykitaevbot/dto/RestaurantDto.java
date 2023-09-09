package com.tgproject.deliverykitaevbot.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class RestaurantDto implements Serializable {

    private Long id;
    @NotEmpty
    private String name;

}

package com.tgproject.deliverykitaevbot.dto;

import com.tgproject.deliverykitaevbot.model.UserState;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class InlineMenuDto implements Serializable {

    private Long id;
    @NotNull
    private Long restaurantId;
    @NotNull
    @NotBlank
    private String tagCallback;
    @NotNull
    @NotBlank
    private String question;
    @NotNull
    @NotBlank
    private String answer;
    @NotNull
    @NotBlank
    private String button;
    private UserState stateId;
    private UserState stateIdNext;
    @NotNull
    private Integer priority;
}

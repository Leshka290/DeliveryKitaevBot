package com.tgproject.deliverykitaevbot.dto;

import com.tgproject.deliverykitaevbot.model.UserState;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class InlineMenuDto {

    private Long id;
    @NotNull
    private Long languageId;
    @NotNull
    private Long shelterId;
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

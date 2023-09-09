package com.tgproject.deliverykitaevbot.mapper;

import com.tgproject.deliverykitaevbot.dto.UserStateDto;
import com.tgproject.deliverykitaevbot.model.UserState;

public interface UserStateMapper {

    UserStateDto toDto(UserState userState);

    UserState toEntity(UserStateDto userStateDto);
}

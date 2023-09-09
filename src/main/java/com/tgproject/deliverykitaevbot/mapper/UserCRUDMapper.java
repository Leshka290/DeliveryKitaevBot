package com.tgproject.deliverykitaevbot.mapper;

import com.tgproject.deliverykitaevbot.dto.UserCRUDDto;
import com.tgproject.deliverykitaevbot.model.User;

public interface UserCRUDMapper {

    User toEntity(UserCRUDDto dto);

    UserCRUDDto toDto(User user);
}

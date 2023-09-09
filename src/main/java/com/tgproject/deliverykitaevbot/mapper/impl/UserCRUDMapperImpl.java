package com.tgproject.deliverykitaevbot.mapper.impl;

import com.tgproject.deliverykitaevbot.dto.UserCRUDDto;
import com.tgproject.deliverykitaevbot.dto.UserDto;
import com.tgproject.deliverykitaevbot.mapper.UserCRUDMapper;
import com.tgproject.deliverykitaevbot.model.User;
import org.modelmapper.ModelMapper;

public class UserCRUDMapperImpl implements UserCRUDMapper {

    ModelMapper mapper = new ModelMapper();
    @Override
    public User toEntity(UserCRUDDto dto) {
        return mapper.map(dto, User.class);
    }

    @Override
    public UserCRUDDto toDto(User user) {
        return mapper.map(user, UserCRUDDto.class);
    }
}

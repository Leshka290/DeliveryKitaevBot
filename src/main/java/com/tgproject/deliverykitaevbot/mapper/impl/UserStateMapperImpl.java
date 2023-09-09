package com.tgproject.deliverykitaevbot.mapper.impl;

import com.tgproject.deliverykitaevbot.dto.UserStateDto;
import com.tgproject.deliverykitaevbot.mapper.UserStateMapper;
import com.tgproject.deliverykitaevbot.model.UserState;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserStateMapperImpl implements UserStateMapper {

    ModelMapper mapper = new ModelMapper();
    @Override
    public UserStateDto toDto(UserState userState) {
        return mapper.map(userState, UserStateDto.class);
    }

    @Override
    public UserState toEntity(UserStateDto dto) {
        return mapper.map(dto,UserState.class);
    }
}

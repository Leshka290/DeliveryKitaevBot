package com.tgproject.deliverykitaevbot.mapper;

import com.tgproject.deliverykitaevbot.dto.UserStateDto;
import com.tgproject.deliverykitaevbot.model.UserState;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserStateMapper {

    UserStateDto map(UserState userState);

    @InheritInverseConfiguration
    UserState map(UserStateDto userStateDto);
}

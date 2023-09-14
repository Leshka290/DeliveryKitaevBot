package com.tgproject.deliverykitaevbot.mapper;

import com.tgproject.deliverykitaevbot.dto.UserCRUDDto;
import com.tgproject.deliverykitaevbot.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCRUDMapper {

    UserCRUDDto map(User user);

    @InheritInverseConfiguration
    User map(UserCRUDDto dto);
}

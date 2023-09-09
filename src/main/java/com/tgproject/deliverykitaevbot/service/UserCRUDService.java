package com.tgproject.deliverykitaevbot.service;

import com.tgproject.deliverykitaevbot.dto.UserCRUDDto;

import java.util.Collection;

public interface UserCRUDService {

    UserCRUDDto getUser(Long id);

    Collection<UserCRUDDto> getAllUsers();

    UserCRUDDto updateUser(UserCRUDDto userCRUDDto);

    UserCRUDDto deleteUser(Long id);
}

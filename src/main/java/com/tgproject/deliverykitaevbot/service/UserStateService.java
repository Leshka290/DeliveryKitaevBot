package com.tgproject.deliverykitaevbot.service;

import com.tgproject.deliverykitaevbot.dto.UserStateDto;

import java.util.Collection;

public interface UserStateService {

    /**
     * Получить все статусы
     *
     * @return Collection<UserStateDto>
     */
    Collection<UserStateDto> getAllUserStates();

    /**
     * Получить статус по ID
     *
     * @param id Long
     * @return UserStateDto
     */
    UserStateDto getUserState(Long id);

    /**
     * Сохранить новый статус
     *
     * @param userStateDto UserStateDto
     * @return UserStateDto
     */
    UserStateDto save(UserStateDto userStateDto);

    /**
     * Обновить статус
     *
     * @param userStateDto UserStateDto
     * @return UserStateDto
     */
    UserStateDto update(UserStateDto userStateDto);

    /**
     * Удалить статус
     *
     * @param id Long
     * @return UserStateDto
     */
    UserStateDto removeUserState(Long id);
}

package com.tgproject.deliverykitaevbot.crm;

import com.pengrad.telegrambot.model.Update;
import com.tgproject.deliverykitaevbot.model.User;

public interface UserService {

    /**
     * Получение телеграм ID пользователя
     *
     * @param update событие на сервере
     * @return idUser
     */
    Long getIdUser(Update update);

    User update(User user);

    /**
     * Поиск или создание пользователя
     *
     * @param id Long
     * @return пользователь
     */
    User findUserOrCreate(Long id);
}

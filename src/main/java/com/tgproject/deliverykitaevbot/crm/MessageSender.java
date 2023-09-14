package com.tgproject.deliverykitaevbot.crm;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.tgproject.deliverykitaevbot.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageSender {

    private final TelegramBot bot;
    private final UserService userService;


    /**
     * Отправка сообщения и сохранение message-Id пользователю
     * @param message Сообшение для отправки
     * @param user User
     * @return User
     */
    public User sendMessage(SendMessage message, User user) {
        SendResponse execute = bot.execute(message);
        if (execute != null) {
            user.setLastResponseStateMenuId(execute.message().messageId().longValue());
        }
        User update = userService.update(user);
        log.debug("User after send and save: {}", user);
        return update;
    }
    /**
     * Отправка сообщения и сохранение message-Id пользователю
     * @param message Сообшение для отправки
     * @param user User
     * @return User
     */
    public User sendMessage(EditMessageText message, User user) {
        SendResponse execute = (SendResponse) bot.execute(message);
        if (execute != null) {
            user.setLastResponseStateMenuId(execute.message().messageId().longValue());
        }
        User update = userService.update(user);
        log.debug("User after send and save: {}", user);
        return update;
    }

    public User sendMessage(SendDocument message, User user) {
        SendResponse execute = bot.execute(message);
        if (execute != null) {
            user.setLastResponseStateMenuId(execute.message().messageId().longValue());
        }
        User update = userService.update(user);
        log.debug("User after send and save: {}", user);
        return update;
    }

    /**
     * Удаление последнего сообщения пользователя
     * @param user User
     */
    public void deleteOldMenu(User user) {
        if (user.getLastResponseStateMenuId() != null) {
            DeleteMessage deleteMessage = new DeleteMessage(user.getChatId(), user.getLastResponseStateMenuId().intValue());
            bot.execute(deleteMessage);
        }
    }

}

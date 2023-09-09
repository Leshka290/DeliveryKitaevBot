package com.tgproject.deliverykitaevbot.crm;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.ForwardMessage;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.tgproject.deliverykitaevbot.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupportService {

    private final TelegramBot bot;
    @Value("${telegram.bot.support.chat}")
    private Long supportChat;

    /**
     * Отправка сообщений в чат поддержки
     *
     * @param update Update
     * @param user   User
     * @return new SendMessage
     */
    public SendMessage sendToSupport(Update update, User user) {
        ForwardMessage forwardMessage =
                new ForwardMessage(supportChat, update.message().chat().id(), update.message().messageId());
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(update.message().chat().id()).append(",\n");
        if (user.getPhone() != null) {
            sb.append("phone: ").append(user.getPhone()).append(",\n");
        }
        if (update.message().text() != null) {
            sb.append("message: ").append(update.message().text());
        }
        SendMessage message = new SendMessage(supportChat, sb.toString());
        if (update.message().text() == null) {
            SendResponse execute = bot.execute(forwardMessage);
            log.info("Forward message response: {}", execute);
        }
        return message;
    }

    /**
     * Отправка сообщения из чата поддержки пользователю
     *
     * @param update Update
     * @return new SendMessage
     */
    public SendMessage sendToUser(Update update) {
        if (update.message().replyToMessage().forwardFrom() == null) {
            log.debug("Пользователь скрыл свой аккаунт от пересылки");
            if (update.message().replyToMessage().text() != null && update.message().text() != null) {
                log.debug("replyToMessage text:  {}", update.message().replyToMessage().text());
                log.debug("text: {}", update.message().text());
                Long id = parseUserId(update.message().replyToMessage().text());
                if (id != null) {
                    return new SendMessage(id, update.message().text());
                }
            }
        } else {
            Long id = update.message().replyToMessage().forwardFrom().id();
            log.debug("update.message.replyToMessage.forwardFrom.id:  {}", id);
            return new SendMessage(id, update.message().text());
        }
        return new SendMessage(supportChat, "Не удалось отправить сообщение. Пользователь скрыл свои данные.\n" +
                "Ответьте на сообщение с id  пользователя");
    }

    /**
     * Проверка на то что сообщение из чата поддержки
     *
     * @param update Update
     * @return boolean
     */
    public boolean isSupportChat(Update update) {
        if (update.message() != null && update.message().chat().id() != null) {
            return update.message().chat().id().equals(supportChat);
        }
        return false;
    }

    /**
     * Парсит текст сообщения из чата поддержки и достает ID
     *
     * @param msg сообщение в чате поддержки
     * @return Ид юзера
     */
    public Long parseUserId(String msg) {
        // id: 1000100100,
        Pattern pattern = Pattern.compile("id: (\\d*),");
        Matcher matcher = pattern.matcher(msg);
        if (!matcher.find()) {
            return null;
        }
        return Long.valueOf(matcher.group(1));
    }
}

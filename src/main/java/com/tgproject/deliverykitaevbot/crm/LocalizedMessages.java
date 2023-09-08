package com.tgproject.deliverykitaevbot.crm;

import com.pengrad.telegrambot.model.Update;
import com.tgproject.deliverykitaevbot.model.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class LocalizedMessages {

    private final String resource = "message";

    /**
     * Получение сообщений по имени из файла message.properties
     * @param name String
     * @param user User
     * @return message
     */
    @SneakyThrows
    public String get(String name, User user) {
        Locale locale = new Locale("ru");
        return ResourceBundle.getBundle(resource, locale).getString(name);
    }

    /**
     * Получение сообщений по имени из файла message.properties
     * @param name String
     * @param update Update
     * @return message
     */
    public String get(String name, Update update) {
        if (update.message() != null && update.message().from() != null) {
            String languageCode = update.message().from().languageCode();
            Locale locale = new Locale(languageCode, languageCode);
            return ResourceBundle.getBundle(resource, locale).getString(name);
        }
        Locale locale = new Locale("ru");
        return ResourceBundle.getBundle(resource, locale).getString(name);
    }

    public String get(String name) {
        Locale locale = new Locale("ru");
        return ResourceBundle.getBundle(resource, locale).getString(name);
    }
}

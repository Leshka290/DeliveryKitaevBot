package com.tgproject.deliverykitaevbot.config;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.DeleteMyCommands;
import lombok.Data;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;

/**
 * Конфигурация телеграм-бота.
 *
 */

@Configuration
@Data
@PropertySource("application.properties")
public class TelegramBotConfiguration {

    @Value("${telegram.bot.token}")
    private String token;

    @Value("${bot.owner}")
    private Long ownerId;

    @Bean
    public TelegramBot telegramBot() {
        TelegramBot bot = new TelegramBot(token);
        bot.execute(new DeleteMyCommands());
        return bot;
    }

    @Value("${bot.name}")
    private String botName;


}

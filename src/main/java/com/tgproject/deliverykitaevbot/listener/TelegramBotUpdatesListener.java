package com.tgproject.deliverykitaevbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.tgproject.deliverykitaevbot.crm.TelegramFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Реализует функционал телеграм-бота.
 */
@Service
@Slf4j
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramFacade botFacade;
    private final TelegramBot bot;

    public TelegramBotUpdatesListener(TelegramFacade botFacade, TelegramBot bot) {
        this.botFacade = botFacade;
        this.bot = bot;
    }

    @PostConstruct
    public void init() {
        bot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            log.info("Processing update: {}", update);
            try {
                botFacade.processUpdate(update);
            } catch (Exception exception) {
                log.info("exception: {}", exception.getMessage());
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}

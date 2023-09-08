package com.tgproject.deliverykitaevbot.crm;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.tgproject.deliverykitaevbot.model.InlineMenu;
import com.tgproject.deliverykitaevbot.model.UserState;
import com.tgproject.deliverykitaevbot.repository.InlineMenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Построение меню бота
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class InlineBuilder {

    private final InlineMenuRepository inlineMenuRepository;

    /**
     * Формирование меню с переходами
     *
     * @param menu InlineMenu
     * @return InlineKeyboardMarkup
     */
    public InlineKeyboardMarkup getInlineMenu(InlineMenu menu) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        UserState state;
        if (menu.getStateIdNext() != null) {
            state = menu.getStateIdNext();
        } else {
            state = menu.getStateId();
        }
        List<InlineMenu> answerList = inlineMenuRepository.findAllByStateId(state);
        answerList.sort(Comparator.comparing(InlineMenu::getPriority));
        answerList.forEach(telegramAnswer -> {
            if (telegramAnswer.getButton() != null &&
                    telegramAnswer.getTagCallback() != null &&
                    telegramAnswer.getButton().length() > 0 &&
                    telegramAnswer.getTagCallback().length() > 0) {
                keyboardMarkup.addRow(new InlineKeyboardButton(telegramAnswer.getButton())
                        .callbackData(telegramAnswer.getTagCallback()));
            }
        });
        log.debug("InlineKeyboardMarkup: {}", keyboardMarkup);
        return keyboardMarkup;
    }

    /**
     * Добавление кнопки выход.
     *
     * @return new InlineKeyboardMarkup()
     */
    public InlineKeyboardMarkup getInlineMenuExit() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("EXIT").callbackData("EXIT"));
        return keyboardMarkup;
    }
}

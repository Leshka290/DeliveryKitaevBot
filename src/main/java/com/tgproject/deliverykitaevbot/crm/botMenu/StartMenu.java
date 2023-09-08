package com.tgproject.deliverykitaevbot.crm.botMenu;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.tgproject.deliverykitaevbot.crm.InlineBuilder;
import com.tgproject.deliverykitaevbot.crm.LocalizedMessages;
import com.tgproject.deliverykitaevbot.crm.UserService;
import com.tgproject.deliverykitaevbot.model.InlineMenu;
import com.tgproject.deliverykitaevbot.model.User;
import com.tgproject.deliverykitaevbot.repository.InlineMenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class StartMenu {

    private final InlineMenuRepository inlineMenuRepository;
    private final InlineBuilder inlineBuilder;
    private final UserService userService;
    private final LocalizedMessages lang;

    /**
     * Постронение меню приюта в соответствии с выбором
     * и смена статуса пользователя
     *
     * @param user User
     * @return new SendMessage с меню
     */
    public SendMessage getSendMessageStartMenu(@NotNull User user) {
        String defaultMsg = lang.get("start", user);
        InlineMenu inlineMenu = new InlineMenu();
        Optional<InlineMenu> menuOptional =
                inlineMenuRepository.findFirstByRestaurantIdAndQuestion(
                        user.getRestaurantId(),
                        "/start");
        if (menuOptional.isPresent()) {
            inlineMenu = menuOptional.get();
            defaultMsg = menuOptional.get().getAnswer();
            user.setStateId(inlineMenu.getStateIdNext());
            userService.update(user);
        }
        InlineKeyboardMarkup keyboardMarkup = inlineBuilder.getInlineMenu(inlineMenu);
        return new SendMessage(user.getChatId(), defaultMsg).replyMarkup(keyboardMarkup);
    }

    /**
     * Постронение меню приюта в соответствии с выбором нажатой кнопки
     * и смена статуса пользователя
     *
     * @param user User
     * @return EditMessageText с меню
     */
    public EditMessageText getEditMessageStartMenu(@NotNull User user) {
        String defaultMsg = lang.get("start", user);
        InlineMenu inlineMenu = new InlineMenu();
        Optional<InlineMenu> menuOptional =
                inlineMenuRepository.findFirstByRestaurantIdAndQuestion(
                        user.getRestaurantId(),
                        "/start");
        if (menuOptional.isPresent()) {
            inlineMenu = menuOptional.get();
            defaultMsg = menuOptional.get().getAnswer();
            user.setStateId(inlineMenu.getStateIdNext());
            userService.update(user);
        }
        InlineKeyboardMarkup keyboardMarkup = inlineBuilder.getInlineMenu(inlineMenu);
        return new EditMessageText(user.getChatId(),
                user.getLastResponseStateMenuId().intValue(),
                defaultMsg).replyMarkup(keyboardMarkup);
    }
}

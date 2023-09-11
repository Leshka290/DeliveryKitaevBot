package com.tgproject.deliverykitaevbot.crm;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.tgproject.deliverykitaevbot.crm.botMenu.StartMenu;
import com.tgproject.deliverykitaevbot.model.InlineMenu;
import com.tgproject.deliverykitaevbot.model.User;
import com.tgproject.deliverykitaevbot.model.UserState;
import com.tgproject.deliverykitaevbot.model.constant.UserStateSpecial;
import com.tgproject.deliverykitaevbot.repository.UserStateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import static com.tgproject.deliverykitaevbot.model.constant.UserStateSpecial.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class SpecialService {

    private final UserStateRepository userStateRepository;
    private final UserService userService;
    private final MessageSender messageSender;
    private final StartMenu startMenu;
    private final InlineBuilder inlineBuilder;
    private final LocalizedMessages lang;
    private final TelegramBot bot;
    private final SupportService supportService;

    /**
     * Метод обрабатывает сообщения, если у пользователя запущен специальный статус
     *
     * @param user   user
     * @param update telegram update
     */
    public void checkSpecialStatus(@NotNull User user, Update update) {
        if (user.getStateId() == null) {
            return;
        }
        String tag = null;
        if (update.callbackQuery() != null) {
            tag = update.callbackQuery().data();
        }

        UserStateSpecial stateSpecial = user.getStateId().getTagSpecial();

        String message = "";
        if (update.message() != null) {
            message = update.message().text();
        }

        //Выбор ресторана
        if (stateSpecial.equals(SELECT_RESTAURANT) && tag != null) {
            user.setRestaurantId(Long.parseLong(tag));
            userService.update(user);
            messageSender.sendMessage(startMenu.getEditMessageStartMenu(user), user);
            return;
        }

        //Обработка телефона
        if (stateSpecial.equals(GET_PHONE_STARTED)) {
            messageSender.deleteOldMenu(user);
            user.setPhone(message);
            userService.update(user);
            bot.execute(new SendMessage(user.getChatId(), lang.get("answer_get_phone") + message));
            messageSender.sendMessage(startMenu.getSendMessageStartMenu(user), user);
            return;
        }

        //Обработка переписки в чате
        if (stateSpecial.equals(SUPPORT_CHAT_STARTED)) {
            bot.execute(supportService.sendToSupport(update, user));
            return;
        }

        message = lang.get("start");
        SendMessage sendMessage = new SendMessage(user.getChatId(), message);
        messageSender.sendMessage(sendMessage, user);
    }

    /**
     * Метод обрабатывает первичное нажатие из inline меню,
     * и запускает процесс уже специальной обработки
     *
     * @param user   user
     * @param update update
     * @param menu   InlineMenu
     */
    public void checkSpecialStatusInMenu(@NotNull User user, Update update, InlineMenu menu) {
        UserStateSpecial stateSpecial = user.getStateId().getTagSpecial();

        String tag = "";
        if (update.callbackQuery() != null) {
            tag = update.callbackQuery().data();
        }

        if (stateSpecial.equals(GET_PHONE)) {
            messageSender.deleteOldMenu(user);
            UserState userState = getUserState(GET_PHONE_STARTED);
            user.setStateId(userState);
            userService.update(user);
            SendMessage sendMessage = new SendMessage(user.getChatId(), menu.getAnswer());
            messageSender.sendMessage(sendMessage, user);
            return;
        }


        //Обработка начала чата
        if (stateSpecial.equals(SUPPORT_CHAT)) {
            messageSender.deleteOldMenu(user);
            UserState userState = getUserState(SUPPORT_CHAT_STARTED);
            user.setStateId(userState);
            userService.update(user);

            SendMessage sendMessage = new SendMessage(user.getChatId(), lang.get("chat_exit_btn", user))
                    .replyMarkup(new ReplyKeyboardMarkup(
                            new KeyboardButton("\uD83D\uDD1A"))
                            .resizeKeyboard(true)
                            .selective(true));

            bot.execute(sendMessage);
            return;
        }
            //Генерируем меню
        //Изменить меню
            InlineKeyboardMarkup inlineMenuReport = inlineBuilder.getInlineMenu(menu);

            EditMessageText editInlineMessageText = new EditMessageText(user.getChatId(),
                    user.getLastResponseStateMenuId().intValue(),
                    "Дата: " + user.getDtCreate().truncatedTo(ChronoUnit.DAYS).format(DateTimeFormatter
                            .ofPattern("dd MMMM yyyy", Locale.getDefault())) + "\n" +
                            "Отчет по питомцу: " + user.getName() + "\n" +
                            "выберите пункт меню, для отправки отчета").replyMarkup(inlineMenuReport);
            messageSender.sendMessage(editInlineMessageText, user);
            return;

        }

    private UserState getUserState(UserStateSpecial stateSpecial) {
        return userStateRepository.findFirstByTagSpecial(stateSpecial).orElse(null);
    }
}

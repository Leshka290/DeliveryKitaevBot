package com.tgproject.deliverykitaevbot.crm;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardRemove;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.tgproject.deliverykitaevbot.crm.botMenu.RestaurantMenu;
import com.tgproject.deliverykitaevbot.crm.botMenu.StartMenu;
import com.tgproject.deliverykitaevbot.model.InlineMenu;
import com.tgproject.deliverykitaevbot.model.User;
import com.tgproject.deliverykitaevbot.model.constant.UserStateSpecial;
import com.tgproject.deliverykitaevbot.repository.InlineMenuRepository;
import com.tgproject.deliverykitaevbot.repository.UserStateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class TelegramFacade {

    private final TelegramBot bot;
    private final MessageSender messageSender;
    private final StartMenu startMenu;
    private final InlineBuilder inlineBuilder;
    private final UserService userService;
//    private final SpecialService specialService;
//    private final SupportService supportService;
    private final RestaurantMenu restaurantMenu;
    private final InlineMenuRepository inlineMenuRepository;
    private final UserStateRepository userStateRepository;
    private final LocalizedMessages lang;

    /**
     * Фасад, основная логика построения меню бота и обработки статусов
     * По окончанию метода, отправляем сообщение или редактируем, сохраняем ответ полученный от телеги
     *
     * @param update Update
     */
    public void processUpdate(Update update) {
        String message = getMessage(update);
        String tag = null;
        Long idUser = userService.getIdUser(update);
        User user = userService.findUserOrCreate(idUser);
        EditMessageText editInlineMessageText = null;
        log.trace("idUser {}", idUser);

        if (update.callbackQuery() != null) {
            tag = update.callbackQuery().data();
            log.debug("CallbackQuery: {}", tag);
        }

        // Дефотное сообщение, если мы не распознали команду пользователя
        SendMessage sendMessage = new SendMessage(idUser, lang.get("start", user));

        // Обработка команды /start, начальная точка работы бота
        if (message.startsWith("/start")) {
            user.setRestaurantId(null);
            user.setStateId(userStateRepository.findFirstByTagSpecial(UserStateSpecial.SELECT_RESTAURANT).orElse(null));
            user.setLastResponseStateMenuId(null);
            messageSender.sendMessage(restaurantMenu.getRestaurantMenu(user), user);
            return;
        }
        if (message.startsWith("/test")) {
            log.error("test");

            messageSender.sendMessage(sendMessage, user);
            return;
        }

        // Выйти в главное меню
        if (message.startsWith("\uD83D\uDD1A")) {
            messageSender.sendMessage(new SendMessage(idUser, lang.get("chat_end", user)).replyMarkup(new ReplyKeyboardRemove())
                    , user);
            messageSender.sendMessage(startMenu.getSendMessageStartMenu(user), user);
            return;
        }

        // Выход в главное меню по кнопке инлайн
        if (tag != null && tag.equals("EXIT")) {
            DeleteMessage deleteMessage = new DeleteMessage(user.getChatId(), user.getLastResponseStateMenuId().intValue());
            bot.execute(deleteMessage);

            SendMessage sendMessageStartMenu = startMenu.getSendMessageStartMenu(user);
            messageSender.sendMessage(sendMessageStartMenu, user);
            return;
        }

//        // Отвечает сотрудник чата поддержки. Не логируем, ничего не сохраняем
//        if (supportService.isSupportChat(update)) {
//            bot.execute(supportService.sendToUser(update));
//            return;
//        }

//        // Обработка специальных статусов
//        if (user.getStateId() != null && user.getStateId().getTagSpecial() != null) {
//            specialService.checkSpecialStatus(user, update);
//            return;
//        }

        // есть callback_data
        if (tag != null) {
            Optional<InlineMenu> menuOptional = inlineMenuRepository
                    .findFirstByRestaurantIdAndTagCallback(
                            user.getRestaurantId(),
                            tag
                    );
//            // Получаем меню из базы по TagCallback
//            if (menuOptional.isEmpty() && user.getStateId().getTagSpecial() != null) {
//                specialService.checkSpecialStatus(user, update);
//                return;
//            }
            if (menuOptional.isPresent()) {
                InlineMenu menu = menuOptional.get();
                message = menu.getAnswer();
                // Сетим новый статус
                if (menu.getStateIdNext() != null) {
                    user.setStateId(menu.getStateIdNext());
                }
//                // Обработка кнопки меню, когда есть специальный статус(действие/меню) по нажатию на кнопку
//                if (user.getStateId().getTagSpecial() != null) {
//                    specialService.checkSpecialStatusInMenu(user, update, menu);
//                    return;
//                }

                // Формируем динамическое меню
                InlineKeyboardMarkup inlineMenu = inlineBuilder.getInlineMenu(menu);
                editInlineMessageText = new EditMessageText(idUser,
                        user.getLastResponseStateMenuId().intValue(),
                        message).replyMarkup(inlineMenu);
                messageSender.sendMessage(editInlineMessageText, user);
                return;
            }
        }

        //Отправим сообщение на случай если сбой обработки
        try {
            messageSender.sendMessage(sendMessage, user);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
    }

    /**
     * Инициализация сообщения
     *
     * @param update событие на сервере
     * @return message текст написанный в чат
     */
    private String getMessage(Update update) {
        String message = "^-^";
        if (update.message() != null && update.message().text() != null) {
            message = update.message().text();
        }
        return message;
    }
}

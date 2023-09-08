package com.tgproject.deliverykitaevbot.crm.botMenu;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.tgproject.deliverykitaevbot.model.Restaurant;
import com.tgproject.deliverykitaevbot.model.User;
import com.tgproject.deliverykitaevbot.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestaurantMenu {

    private final RestaurantRepository restaurantRepository;

    /**
     * Построение меню из списка ресторанов
     *
     * @param user User
     * @return new SendMessage с меню
     */
    public SendMessage getRestaurantMenu(@NotNull User user) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        List<Restaurant> restaurantList = restaurantRepository.findAll();
        restaurantList.forEach(restaurant -> {
                    keyboardMarkup.addRow(new InlineKeyboardButton(restaurant.getName())
                            .callbackData(restaurant.getId().toString()));
                }
        );

        log.debug("RestaurantMenu: {}", keyboardMarkup);
        return new SendMessage(user.getChatId(), "Выберите ресторан ").replyMarkup(keyboardMarkup);
    }
}

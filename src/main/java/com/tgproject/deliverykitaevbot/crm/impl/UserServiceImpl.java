package com.tgproject.deliverykitaevbot.crm.impl;

import com.pengrad.telegrambot.model.Update;
import com.tgproject.deliverykitaevbot.crm.UserService;
import com.tgproject.deliverykitaevbot.model.User;
import com.tgproject.deliverykitaevbot.model.constant.UserStateSpecial;
import com.tgproject.deliverykitaevbot.repository.UserRepository;
import com.tgproject.deliverykitaevbot.repository.UserStateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserStateRepository userStateRepository;

    @Override
    public Long getIdUser(Update update) {
        Long idUser = 0L;
        if (update.message() != null && update.message().chat() != null && update.message().chat().id() != null) {
            idUser = update.message().chat().id();
        } else if (update.callbackQuery() != null) {
            idUser = update.callbackQuery().from().id();
        }
        return idUser;
    }

    @Override
    public User update(User user) {
        log.info("Set user state: " + user.getStateId());
        return userRepository.save(user);
    }
    @Override
    public User findUserOrCreate(Long id) {
        return userRepository.findByChatId(id).orElseGet(() -> create(id));
    }

    public User create(Long id) {
        User user = new User();
        user.setChatId(id);
        user.setStateId(userStateRepository.findFirstByTagSpecial(UserStateSpecial.SELECT_RESTAURANT).orElse(null));
        if (user.getDtCreate() == null) {
            user.setDtCreate(OffsetDateTime.now());
        }
        log.info("New user was created: " + user);
        return userRepository.save(user);
    }
}

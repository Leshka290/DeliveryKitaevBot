package com.tgproject.deliverykitaevbot.repository;

import com.tgproject.deliverykitaevbot.model.UserState;
import com.tgproject.deliverykitaevbot.model.constant.UserStateSpecial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStateRepository extends JpaRepository<UserState, Long> {

    Optional<UserState> findFirstByRestaurantIdAndTagSpecial(Long aLong, UserStateSpecial tag);

    /**
     * Найти по спец тэгу
     *
     * @param tag UserStateSpecial
     * @return Optional
     */
    Optional<UserState> findFirstByTagSpecial(UserStateSpecial tag);
}

package com.tgproject.deliverykitaevbot.repository;

import com.tgproject.deliverykitaevbot.model.InlineMenu;
import com.tgproject.deliverykitaevbot.model.UserState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InlineMenuRepository extends JpaRepository<InlineMenu, Long> {

    /**
     * Найти по restaurant, question
     *
     * @param restaurant Long
     * @param question   String
     * @return Optional
     */
    Optional<InlineMenu> findFirstByRestaurantIdAndQuestion(Long restaurant, String question);

    /**
     * Найти по restaurant, tag
     *
     * @param restaurant Long
     * @param tag        String
     * @return Optional
     */
    Optional<InlineMenu> findFirstByRestaurantIdAndTagCallback(Long restaurant, String tag);

    /**
     * Найти по stateId
     *
     * @param stateId UserState
     * @return List<InlineMenu>
     */
    List<InlineMenu> findAllByStateId(UserState stateId);

    Optional<InlineMenu> findFirstByTagCallback(String tag);
}

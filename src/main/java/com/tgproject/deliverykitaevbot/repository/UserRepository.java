package com.tgproject.deliverykitaevbot.repository;

import com.tgproject.deliverykitaevbot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Найти по ID
     *
     * @param id Long
     * @return Optional
     */
    Optional<User> findByChatId(Long id);
}

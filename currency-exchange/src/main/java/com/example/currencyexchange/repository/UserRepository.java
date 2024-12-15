package com.example.currencyexchange.repository;

import com.example.currencyexchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью {@link User}.
 * Обеспечивает доступ к данным о пользователях.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Находит пользователя по его email.
     *
     * @param email Электронная почта пользователя
     * @return Объект Optional, содержащий пользователя, если он найден, или пустой объект, если не найден
     */
    Optional<User> findByEmail(String email);
}

package com.example.currencyexchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.currencyexchange.model.User;
import com.example.currencyexchange.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис для работы с пользователями.
 * Обеспечивает доступ к данным пользователя и выполнение операций с ними.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Находит пользователя по email.
     *
     * @param email Электронная почта пользователя для поиска
     * @return Объект {@link Optional}, содержащий пользователя, если найден, или пустой {@link Optional}, если пользователь не найден
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Сохраняет пользователя в базе данных.
     *
     * @param user Пользователь, которого нужно сохранить
     */
    public void save(User user) {
        userRepository.save(user);
    }
}

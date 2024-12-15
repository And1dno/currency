package com.example.currencyexchange.service;

import com.example.currencyexchange.model.User;
import com.example.currencyexchange.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Сервис для загрузки пользовательских данных для аутентификации.
 * Реализует интерфейс {@link UserDetailsService} для предоставления данных пользователя для Spring Security.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Конструктор для инициализации {@link UserRepository}.
     *
     * @param userRepository Репозиторий для работы с данными пользователей
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Загружает данные пользователя по его email для аутентификации.
     *
     * @param email Электронная почта пользователя, по которой осуществляется поиск
     * @return {@link UserDetails} объект, содержащий информацию о пользователе для Spring Security
     * @throws UsernameNotFoundException Если пользователь с данным email не найден
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email == null || email.isEmpty()) {
            throw new UsernameNotFoundException("Email is empty");
        }

        System.out.println("Attempting to load user with email: " + email);

        // Получаем пользователя по email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Логируем данные о пользователе
        System.out.println("User found: " + user.getEmail());
        System.out.println("User role: " + user.getRole()); // Логируем роль пользователя

        // Преобразуем роль в SimpleGrantedAuthority
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getName());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(authority) // Одна роль пользователя
        );
    }
}

package com.example.currencyexchange.config;

import com.example.currencyexchange.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;

/**
 * Конфигурация безопасности для приложения с использованием Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    /**
     * Конструктор для внедрения {@link CustomUserDetailsService}.
     *
     * @param userDetailsService сервис для загрузки информации о пользователях.
     */
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Конфигурация цепочки фильтров безопасности.
     *
     * @param http объект для настройки безопасности HTTP-запросов.
     * @return настроенный объект {@link SecurityFilterChain}.
     * @throws Exception если возникает ошибка при настройке безопасности.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Отключаем CSRF для упрощения работы с API
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/api/auth/**").permitAll() // Разрешаем доступ к /api/auth/** без аутентификации
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // Статические ресурсы
                        .requestMatchers("/", "/login", "/register").permitAll() // Разрешаем доступ к главной и регистрации без аутентификации
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Разрешаем доступ к административным страницам только для пользователей с ролью ADMIN
                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                )
                .formLogin(form -> form
                        .loginPage("/login") // Страница логина
                        .defaultSuccessUrl("/", true) // Переход на главную страницу после успешного входа
                        .permitAll() // Разрешаем доступ к странице логина без аутентификации
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL для выхода
                        .logoutSuccessUrl("/login") // Переход на страницу логина после выхода
                        .permitAll() // Разрешаем доступ к выходу
                )
                .build();
    }

    /**
     * Бин для кодирования паролей с использованием BCrypt.
     *
     * @return экземпляр {@link BCryptPasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Бин для сервиса загрузки данных о пользователях.
     *
     * @return экземпляр {@link UserDetailsService}.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    /**
     * Бин для менеджера аутентификации.
     *
     * @param http объект для настройки безопасности HTTP-запросов.
     * @return экземпляр {@link AuthenticationManager}.
     * @throws Exception если возникает ошибка при настройке менеджера аутентификации.
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}

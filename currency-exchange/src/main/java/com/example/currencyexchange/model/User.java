package com.example.currencyexchange.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Модель пользователя в системе.
 * Включает информацию о пользователе, его роли и балансах валют.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; // Имя пользователя
    private String password; // Пароль пользователя
    private String email; // Электронная почта пользователя
    private String name; // Имя пользователя

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role; // Роль пользователя

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserCurrencyBalance> currencyBalances; // Баланс пользователя по валютам

    /**
     * Получение ID пользователя.
     *
     * @return ID пользователя
     */
    public Long getId() {
        return id;
    }

    /**
     * Установка ID пользователя.
     *
     * @param id ID пользователя
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получение имени пользователя.
     *
     * @return Имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Установка имени пользователя.
     *
     * @param username Имя пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Получение пароля пользователя.
     *
     * @return Пароль пользователя
     */
    public String getPassword() {
        return password;
    }

    /**
     * Установка пароля пользователя.
     *
     * @param password Пароль пользователя
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Получение электронной почты пользователя.
     *
     * @return Электронная почта пользователя
     */
    public String getEmail() {
        return email;
    }

    /**
     * Установка электронной почты пользователя.
     *
     * @param email Электронная почта пользователя
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Получение имени пользователя.
     *
     * @return Имя пользователя
     */
    public String getName() {
        return name; // Возвращает имя пользователя
    }

    /**
     * Установка имени пользователя.
     *
     * @param name Имя пользователя
     */
    public void setName(String name) {
        this.name = name; // Устанавливает имя пользователя
    }

    /**
     * Получение роли пользователя.
     *
     * @return Роль пользователя
     */
    public Role getRole() {
        return role;
    }

    /**
     * Установка роли пользователя.
     *
     * @param role Роль пользователя
     */
    public void setRole(Role role) {
        this.role = role; // Устанавливает роль пользователя
    }

    /**
     * Получение баланса валют пользователя.
     *
     * @return Список балансов валют пользователя
     */
    public List<UserCurrencyBalance> getCurrencyBalances() {
        return currencyBalances;
    }

    /**
     * Установка баланса валют пользователя.
     *
     * @param currencyBalances Список балансов валют пользователя
     */
    public void setCurrencyBalances(List<UserCurrencyBalance> currencyBalances) {
        this.currencyBalances = currencyBalances; // Устанавливает список балансов валют
    }
}

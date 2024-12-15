package com.example.currencyexchange.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

/**
 * Модель для представления баланса пользователя по валюте.
 * Содержит информацию о балансе пользователя в конкретной валюте.
 */
@Entity
public class UserCurrencyBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user; // Пользователь, которому принадлежит данный баланс

    @ManyToOne
    private Currency currency; // Валюта, для которой хранится баланс

    private double balance; // Баланс в выбранной валюте

    /**
     * Конструктор без параметров.
     * Необходим для JPA.
     */
    public UserCurrencyBalance() {}

    /**
     * Конструктор с параметрами для создания баланса пользователя.
     *
     * @param user Пользователь, которому принадлежит баланс
     * @param currency Валюта, для которой хранится баланс
     * @param balance Сумма баланса в указанной валюте
     */
    public UserCurrencyBalance(User user, Currency currency, double balance) {
        this.user = user;
        this.currency = currency;
        this.balance = balance;
    }

    /**
     * Получение ID баланса.
     *
     * @return ID баланса
     */
    public Long getId() {
        return id;
    }

    /**
     * Установка ID баланса.
     *
     * @param id ID баланса
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получение пользователя, которому принадлежит баланс.
     *
     * @return Пользователь
     */
    public User getUser() {
        return user;
    }

    /**
     * Установка пользователя, которому принадлежит баланс.
     *
     * @param user Пользователь
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Получение валюты, для которой хранится баланс.
     *
     * @return Валюта
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Установка валюты, для которой хранится баланс.
     *
     * @param currency Валюта
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Получение баланса в выбранной валюте.
     *
     * @return Баланс
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Установка баланса в выбранной валюте.
     *
     * @param balance Баланс
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}

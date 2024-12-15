package com.example.currencyexchange.model;

import jakarta.persistence.*;

/**
 * Модель для представления баланса пользователя по определенной валюте.
 */
@Entity
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    private Double amount; // Сумма валюты

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
     * @return Пользователь, которому принадлежит баланс
     */
    public User getUser() {
        return user;
    }

    /**
     * Установка пользователя для баланса.
     *
     * @param user Пользователь, которому принадлежит баланс
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Получение валюты, на которую рассчитан баланс.
     *
     * @return Валюта, на которую рассчитан баланс
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Установка валюты для баланса.
     *
     * @param currency Валюта для баланса
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Получение суммы валюты на балансе.
     *
     * @return Сумма валюты
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Установка суммы валюты на балансе.
     *
     * @param amount Сумма валюты
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

package com.example.currencyexchange.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Модель для представления транзакции обмена валют.
 */
@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Пользователь, осуществивший транзакцию

    private String currencyFrom; // Валюта, из которой производится обмен
    private String currencyTo;   // Валюта, в которую производится обмен
    private Double amountFrom;   // Сумма валюты, из которой происходит обмен
    private Double amountTo;     // Сумма валюты, в которую происходит обмен
    private LocalDateTime transactionDate; // Дата и время совершения транзакции

    /**
     * Получение ID транзакции.
     *
     * @return ID транзакции
     */
    public Long getId() {
        return id;
    }

    /**
     * Установка ID транзакции.
     *
     * @param id ID транзакции
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получение пользователя, который совершил транзакцию.
     *
     * @return Пользователь, совершивший транзакцию
     */
    public User getUser() {
        return user;
    }

    /**
     * Установка пользователя, который совершил транзакцию.
     *
     * @param user Пользователь, совершивший транзакцию
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Получение валюты, из которой происходит обмен.
     *
     * @return Валюта, из которой происходит обмен
     */
    public String getCurrencyFrom() {
        return currencyFrom;
    }

    /**
     * Установка валюты, из которой происходит обмен.
     *
     * @param currencyFrom Валюта, из которой происходит обмен
     */
    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    /**
     * Получение валюты, в которую происходит обмен.
     *
     * @return Валюта, в которую происходит обмен
     */
    public String getCurrencyTo() {
        return currencyTo;
    }

    /**
     * Установка валюты, в которую происходит обмен.
     *
     * @param currencyTo Валюта, в которую происходит обмен
     */
    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    /**
     * Получение суммы валюты, из которой происходит обмен.
     *
     * @return Сумма валюты, из которой происходит обмен
     */
    public Double getAmountFrom() {
        return amountFrom;
    }

    /**
     * Установка суммы валюты, из которой происходит обмен.
     *
     * @param amountFrom Сумма валюты, из которой происходит обмен
     */
    public void setAmountFrom(Double amountFrom) {
        this.amountFrom = amountFrom;
    }

    /**
     * Получение суммы валюты, в которую происходит обмен.
     *
     * @return Сумма валюты, в которую происходит обмен
     */
    public Double getAmountTo() {
        return amountTo;
    }

    /**
     * Установка суммы валюты, в которую происходит обмен.
     *
     * @param amountTo Сумма валюты, в которую происходит обмен
     */
    public void setAmountTo(Double amountTo) {
        this.amountTo = amountTo;
    }

    /**
     * Получение даты и времени совершения транзакции.
     *
     * @return Дата и время транзакции
     */
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    /**
     * Установка даты и времени совершения транзакции.
     *
     * @param transactionDate Дата и время транзакции
     */
    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}

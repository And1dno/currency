package com.example.currencyexchange.model;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Модель для представления валюты.
 */
@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Название валюты
    private String code; // Код валюты

    private double exchangeRate; // Курс обмена валюты

    /**
     * Получение ID валюты.
     *
     * @return ID валюты
     */
    public Long getId() {
        return id;
    }

    /**
     * Установка ID валюты.
     *
     * @param id ID валюты
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получение названия валюты.
     *
     * @return Название валюты
     */
    public String getName() {
        return name;
    }

    /**
     * Установка названия валюты.
     *
     * @param name Название валюты
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получение кода валюты.
     *
     * @return Код валюты
     */
    public String getCode() {
        return code;
    }

    /**
     * Установка кода валюты.
     *
     * @param code Код валюты
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Получение курса обмена валюты.
     *
     * @return Курс обмена валюты
     */
    public double getExchangeRate() {
        return exchangeRate;
    }

    /**
     * Установка курса обмена валюты.
     *
     * @param exchangeRate Курс обмена валюты
     */
    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}

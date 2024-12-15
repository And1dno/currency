package com.example.currencyexchange.service;

import com.example.currencyexchange.model.Currency;
import com.example.currencyexchange.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с валютами.
 * Предоставляет методы для получения валют и их курсов.
 */
@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    /**
     * Получает все валюты с их курсами.
     *
     * @return Список всех валют с их текущими курсами
     */
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }
}

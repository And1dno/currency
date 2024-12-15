package com.example.currencyexchange.service;

import com.example.currencyexchange.model.Currency;
import com.example.currencyexchange.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для обмена валют.
 * Обеспечивает обмен одной валюты на другую с учетом их обменных курсов.
 */
@Service
public class CurrencyExchangeService {

    @Autowired
    private CurrencyRepository currencyRepository;

    /**
     * Производит обмен валюты с учетом обменных курсов.
     *
     * @param fromCurrencyCode Код исходной валюты
     * @param toCurrencyCode Код целевой валюты
     * @param amount Сумма для обмена
     * @return Сумма в целевой валюте после обмена
     * @throws RuntimeException Если одна из валют не найдена в базе данных
     */
    public double exchangeCurrency(String fromCurrencyCode, String toCurrencyCode, double amount) {
        // Получаем валюту-источник
        Currency fromCurrency = currencyRepository.findByCode(fromCurrencyCode)
                .orElseThrow(() -> new RuntimeException("Currency with code " + fromCurrencyCode + " not found"));

        // Получаем целевую валюту
        Currency toCurrency = currencyRepository.findByCode(toCurrencyCode)
                .orElseThrow(() -> new RuntimeException("Currency with code " + toCurrencyCode + " not found"));

        // Производим расчет
        double rate = toCurrency.getExchangeRate() / fromCurrency.getExchangeRate();
        return amount * rate;
    }
}

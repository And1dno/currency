package com.example.currencyexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс приложения для запуска приложения обмена валют.
 * Включает точку входа для запуска Spring Boot приложения.
 */
@SpringBootApplication
public class CurrencyExchangeApplication {

    /**
     * Точка входа для запуска приложения.
     *
     * @param args Параметры командной строки, передаваемые при запуске приложения
     */
    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangeApplication.class, args);
    }

}

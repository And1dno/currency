package com.example.currencyexchange.model;

import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Модель для представления курса валюты в определенный момент времени.
 */
@Entity
@Data
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Currency currency; // Валюта, к которой привязан курс

    private BigDecimal rate; // Курс валюты

    private LocalDateTime timestamp; // Время, когда был зафиксирован курс

    /**
     * Получение ID курса валюты.
     *
     * @return ID курса валюты
     */
    public Long getId() {
        return id;
    }

    /**
     * Установка ID курса валюты.
     *
     * @param id ID курса валюты
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получение валюты, для которой задан курс.
     *
     * @return Валюта, для которой задан курс
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Установка валюты, для которой задается курс.
     *
     * @param currency Валюта, для которой задается курс
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Получение курса валюты.
     *
     * @return Курс валюты
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Установка курса валюты.
     *
     * @param rate Курс валюты
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * Получение временной метки курса валюты.
     *
     * @return Время, когда был зафиксирован курс
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Установка временной метки курса валюты.
     *
     * @param timestamp Время, когда был зафиксирован курс
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

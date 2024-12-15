package com.example.currencyexchange.repository;

import com.example.currencyexchange.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью {@link Currency}.
 * Обеспечивает доступ к данным о валютах.
 */
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    /**
     * Находит валюту по ее коду.
     *
     * @param code Код валюты
     * @return Объект Optional, содержащий валюту, если она найдена, или пустой объект, если не найдена
     */
    Optional<Currency> findByCode(String code);
}

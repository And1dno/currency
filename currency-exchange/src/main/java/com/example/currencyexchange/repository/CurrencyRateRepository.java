package com.example.currencyexchange.repository;

import com.example.currencyexchange.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностью {@link CurrencyRate}.
 * Обеспечивает доступ к данным о курсах валют.
 */
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {
    // Дополнительные методы для работы с курсами валют могут быть добавлены здесь
}

package com.example.currencyexchange.repository;

import com.example.currencyexchange.model.User;
import com.example.currencyexchange.model.UserCurrencyBalance;
import com.example.currencyexchange.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью {@link UserCurrencyBalance}.
 * Обеспечивает доступ к данным о балансе пользователей в разных валютах.
 */
public interface UserCurrencyBalanceRepository extends JpaRepository<UserCurrencyBalance, Long> {

    /**
     * Находит баланс пользователя по указанной валюте.
     *
     * @param user Пользователь, чье значение баланса ищем
     * @param currency Валюта, по которой ищется баланс
     * @return Объект Optional, содержащий баланс, если он найден, или пустой объект, если не найден
     */
    Optional<UserCurrencyBalance> findByUserAndCurrency(User user, Currency currency);
}

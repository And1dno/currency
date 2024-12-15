package com.example.currencyexchange.service;

import com.example.currencyexchange.model.UserCurrencyBalance;
import com.example.currencyexchange.repository.UserCurrencyBalanceRepository;
import com.example.currencyexchange.repository.CurrencyRepository;
import com.example.currencyexchange.repository.UserRepository;
import com.example.currencyexchange.model.Currency;
import com.example.currencyexchange.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с балансом пользователя в разных валютах.
 * Обеспечивает увеличение и уменьшение баланса валюты.
 */
@Service
public class BalanceService {

    @Autowired
    private UserCurrencyBalanceRepository balanceRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Увеличивает баланс пользователя по указанной валюте.
     *
     * @param userId Идентификатор пользователя
     * @param currencyCode Код валюты, баланс которой нужно увеличить
     * @param amount Сумма, на которую нужно увеличить баланс
     * @throws RuntimeException Если пользователь или валюта не найдены, или если у пользователя нет этой валюты
     */
    public void increaseBalance(Long userId, String currencyCode, double amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Currency currency = currencyRepository.findByCode(currencyCode)
                .orElseThrow(() -> new RuntimeException("Currency not found"));

        UserCurrencyBalance balance = balanceRepository.findByUserAndCurrency(user, currency)
                .orElseThrow(() -> new RuntimeException("User does not have this currency"));

        balance.setBalance(balance.getBalance() + amount);
        balanceRepository.save(balance);
    }

    /**
     * Уменьшает баланс пользователя по указанной валюте.
     *
     * @param userId Идентификатор пользователя
     * @param currencyCode Код валюты, баланс которой нужно уменьшить
     * @param amount Сумма, на которую нужно уменьшить баланс
     * @throws RuntimeException Если пользователь или валюта не найдены, или если у пользователя нет достаточно средств
     */
    public void decreaseBalance(Long userId, String currencyCode, double amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Currency currency = currencyRepository.findByCode(currencyCode)
                .orElseThrow(() -> new RuntimeException("Currency not found"));

        UserCurrencyBalance balance = balanceRepository.findByUserAndCurrency(user, currency)
                .orElseThrow(() -> new RuntimeException("User does not have this currency"));

        if (balance.getBalance() < amount) {
            throw new RuntimeException("Not enough balance");
        }

        balance.setBalance(balance.getBalance() - amount);
        balanceRepository.save(balance);
    }
}

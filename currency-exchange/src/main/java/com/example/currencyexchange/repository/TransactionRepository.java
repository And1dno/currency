package com.example.currencyexchange.repository;

import com.example.currencyexchange.model.Transaction;
import com.example.currencyexchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для работы с сущностью {@link Transaction}.
 * Обеспечивает доступ к данным о транзакциях пользователей.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Находит все транзакции, связанные с указанным пользователем.
     *
     * @param user Пользователь, чьи транзакции нужно найти
     * @return Список транзакций пользователя
     */
    List<Transaction> findByUser(User user);
}

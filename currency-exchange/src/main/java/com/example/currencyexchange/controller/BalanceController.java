package com.example.currencyexchange.controller;

import com.example.currencyexchange.service.BalanceService;
import com.example.currencyexchange.repository.CurrencyRepository;
import com.example.currencyexchange.repository.UserRepository;
import com.example.currencyexchange.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

/**
 * Контроллер для обработки операций с балансом пользователя,
 * таких как просмотр, пополнение и снятие средств.
 */
@Controller
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Отображает текущий баланс аутентифицированного пользователя.
     *
     * @param user  текущий аутентифицированный пользователь.
     * @param model модель для передачи информации о пользователе и балансе.
     * @return имя шаблона представления для отображения информации о балансе.
     */
    @GetMapping
    public String showBalance(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("currencies", currencyRepository.findAll());
        model.addAttribute("balances", user.getCurrencyBalances()); // Добавляем балансы пользователя
        return "balance"; // Шаблон для отображения баланса
    }

    /**
     * Отображает форму для пополнения баланса пользователя.
     *
     * @param user  текущий аутентифицированный пользователь.
     * @param model модель для передачи информации о пользователе и доступных валютах.
     * @return имя шаблона представления для формы пополнения баланса.
     */
    @GetMapping("/deposit")
    public String depositBalanceForm(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("currencies", currencyRepository.findAll());
        return "deposit_balance";
    }

    /**
     * Обрабатывает пополнение баланса пользователя.
     *
     * @param userId       идентификатор пользователя, пополняющего баланс.
     * @param currencyCode код валюты, которую нужно пополнить.
     * @param amount       сумма для пополнения.
     * @return перенаправление на страницу баланса после обработки пополнения.
     */
    @PostMapping("/deposit")
    public String depositBalance(@RequestParam Long userId, @RequestParam String currencyCode, @RequestParam double amount) {
        balanceService.increaseBalance(userId, currencyCode, amount);
        return "redirect:/balance";
    }

    /**
     * Отображает форму для снятия средств с баланса пользователя.
     *
     * @param user  текущий аутентифицированный пользователь.
     * @param model модель для передачи информации о пользователе и доступных валютах.
     * @return имя шаблона представления для формы снятия средств.
     */
    @GetMapping("/withdraw")
    public String withdrawBalanceForm(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("currencies", currencyRepository.findAll());
        return "withdraw_balance";
    }

    /**
     * Обрабатывает снятие средств с баланса пользователя.
     *
     * @param userId       идентификатор пользователя, снимающего средства.
     * @param currencyCode код валюты, которую нужно снять.
     * @param amount       сумма для снятия.
     * @return перенаправление на страницу баланса после обработки снятия.
     */
    @PostMapping("/withdraw")
    public String withdrawBalance(@RequestParam Long userId, @RequestParam String currencyCode, @RequestParam double amount) {
        balanceService.decreaseBalance(userId, currencyCode, amount);
        return "redirect:/balance";
    }
}

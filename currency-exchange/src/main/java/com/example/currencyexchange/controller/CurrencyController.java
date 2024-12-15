package com.example.currencyexchange.controller;

import com.example.currencyexchange.model.Currency;
import com.example.currencyexchange.model.User;
import com.example.currencyexchange.repository.CurrencyRepository;
import com.example.currencyexchange.repository.UserRepository;
import com.example.currencyexchange.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с валютами и их обменом между пользователями.
 */
@Controller
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Отображение списка всех валют.
     *
     * @param model Модель для передачи данных в шаблон
     * @return Имя шаблона для отображения списка валют
     */
    @GetMapping
    public String showCurrencies(Model model) {
        model.addAttribute("currencies", currencyRepository.findAll());
        return "currencies"; // Название шаблона currencies.html
    }

    /**
     * Страница для добавления новой валюты.
     *
     * @param model Модель для передачи данных в шаблон
     * @return Имя шаблона для формы добавления валюты
     */
    @GetMapping("/add")
    public String showAddCurrencyForm(Model model) {
        model.addAttribute("currency", new Currency()); // Создаем пустую валюту для формы
        return "currency_form";
    }

    /**
     * Обработка добавления новой валюты в базу данных.
     *
     * @param currency Валюта, которая будет добавлена
     * @return Перенаправление на страницу списка валют
     */
    @PostMapping("/add")
    public String addCurrency(@ModelAttribute Currency currency) {
        currencyRepository.save(currency); // Сохраняем валюту в базе данных
        return "redirect:/currencies"; // Перенаправляем обратно на список валют
    }

    /**
     * Страница для редактирования существующей валюты.
     *
     * @param id ID валюты, которую нужно редактировать
     * @param model Модель для передачи данных в шаблон
     * @return Имя шаблона для формы редактирования валюты
     */
    @GetMapping("/edit/{id}")
    public String showEditCurrencyForm(@PathVariable("id") Long id, Model model) {
        Currency currency = currencyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid currency id: " + id));
        model.addAttribute("currency", currency);
        return "currency_form";
    }

    /**
     * Обработка редактирования валюты.
     *
     * @param id ID валюты, которую нужно редактировать
     * @param currency Валюта с изменениями, которые нужно сохранить
     * @return Перенаправление на страницу списка валют
     */
    @PostMapping("/edit/{id}")
    public String editCurrency(@PathVariable("id") Long id, @ModelAttribute Currency currency) {
        currency.setId(id); // Устанавливаем ID валюты
        currencyRepository.save(currency); // Сохраняем изменения
        return "redirect:/currencies"; // Перенаправляем обратно на список валют
    }

    /**
     * Удаление валюты.
     *
     * @param id ID валюты, которую нужно удалить
     * @return Перенаправление на страницу списка валют
     */
    @GetMapping("/delete/{id}")
    public String deleteCurrency(@PathVariable("id") Long id) {
        Currency currency = currencyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid currency id: " + id));
        currencyRepository.delete(currency); // Удаление валюты из базы данных
        return "redirect:/currencies"; // Перенаправляем обратно на список валют
    }

    /**
     * Страница для обмена валюты между пользователями.
     *
     * @param model Модель для передачи данных в шаблон
     * @return Имя шаблона для страницы обмена валют
     */
    @GetMapping("/exchange")
    public String showCurrencyExchangeForm(Model model) {
        model.addAttribute("users", userRepository.findAll()); // Добавляем список пользователей
        model.addAttribute("currencies", currencyRepository.findAll()); // Добавляем список валют
        return "currency_exchange"; // Страница для обмена валюты
    }

    /**
     * Обработка обмена валюты между пользователями.
     *
     * @param userId ID пользователя, инициировавшего обмен
     * @param fromCurrencyCode Код валюты, с которой происходит обмен
     * @param toCurrencyCode Код валюты, на которую происходит обмен
     * @param amount Сумма валюты, подлежащая обмену
     * @param model Модель для передачи данных в шаблон
     * @return Имя шаблона для страницы обмена валют
     */
    @PostMapping("/exchange")
    public String exchangeCurrency(@RequestParam Long userId,
                                   @RequestParam String fromCurrencyCode,
                                   @RequestParam String toCurrencyCode,
                                   @RequestParam Double amount,
                                   Model model) {
        String result = currencyExchangeService.exchangeCurrency(userId, fromCurrencyCode, toCurrencyCode, amount);
        model.addAttribute("result", result); // Отображаем результат обмена
        model.addAttribute("users", userRepository.findAll()); // Добавляем список пользователей
        model.addAttribute("currencies", currencyRepository.findAll()); // Добавляем список валют
        return "currency_exchange"; // Перенаправляем на страницу обмена
    }
}

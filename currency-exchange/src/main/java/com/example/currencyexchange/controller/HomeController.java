package com.example.currencyexchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер для работы с домашней страницей и страницами аутентификации.
 */
@Controller
public class HomeController {

    /**
     * Отображение главной страницы.
     *
     * @return Имя шаблона для главной страницы
     */
    @GetMapping("/")
    public String home() {
        return "index"; // Шаблон для главной страницы
    }

    /**
     * Страница для входа в систему.
     *
     * @param error Сообщение об ошибке, если оно есть (неверный логин или пароль)
     * @param model Модель для передачи данных в шаблон
     * @return Имя шаблона для страницы входа
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password."); // Отображаем ошибку при неверных данных
        }
        return "login"; // Шаблон для страницы входа
    }

    /**
     * Страница для регистрации нового пользователя.
     *
     * @return Имя шаблона для страницы регистрации
     */
    @GetMapping("/register")
    public String register() {
        return "register"; // Шаблон для страницы регистрации
    }
}

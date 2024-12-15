package com.example.currencyexchange.controller;

import com.example.currencyexchange.model.Role;
import com.example.currencyexchange.model.User;
import com.example.currencyexchange.repository.RoleRepository;
import com.example.currencyexchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер для управления пользователями, включая регистрацию нового пользователя.
 */
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Обработка регистрации нового пользователя.
     *
     * @param email Адрес электронной почты пользователя
     * @param username Имя пользователя
     * @param name Имя пользователя
     * @param password Пароль пользователя
     * @param roleName Имя роли пользователя
     * @param model Модель для передачи данных в шаблон
     * @return Имя шаблона для страницы регистрации или перенаправление на страницу входа
     */
    @PostMapping("/register")
    public String registerUser(
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String name,
            @RequestParam String password,
            @RequestParam String roleName,
            Model model) {

        // Проверка уникальности email
        if (userRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Пользователь с таким email уже существует.");
            return "register"; // Если email уже существует, возвращаем на страницу регистрации с ошибкой
        }

        // Преобразование строки в RoleName
        Role.RoleName role;
        try {
            role = Role.RoleName.valueOf(roleName.toUpperCase()); // Преобразование строки в перечисление
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Недопустимое имя роли: " + roleName);
            return "register"; // Если роль некорректна, возвращаем на страницу регистрации с ошибкой
        }

        // Получаем роль из базы данных
        Role roleEntity = roleRepository.findByName(role.name())
                .orElseThrow(() -> new RuntimeException("Роль не найдена в базе данных"));

        // Создаем нового пользователя
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setName(name);  // Убедитесь, что этот геттер существует в классе User
        newUser.setPassword(passwordEncoder.encode(password)); // Шифруем пароль
        newUser.setRole(roleEntity); // Устанавливаем роль

        // Сохраняем пользователя
        userRepository.save(newUser);

        // Перенаправление на страницу логина после успешной регистрации
        return "redirect:/login"; // Перенаправляем на страницу логина
    }
}

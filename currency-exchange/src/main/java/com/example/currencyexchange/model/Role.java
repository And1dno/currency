package com.example.currencyexchange.model;

import jakarta.persistence.*;

/**
 * Модель для представления роли пользователя в системе.
 */
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Название роли пользователя

    /**
     * Перечисление для определения типов ролей.
     */
    public enum RoleName {
        ADMIN, // Администратор
        USER   // Пользователь
    }

    /**
     * Получение ID роли.
     *
     * @return ID роли
     */
    public Long getId() {
        return id;
    }

    /**
     * Установка ID роли.
     *
     * @param id ID роли
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получение названия роли.
     *
     * @return Название роли
     */
    public String getName() {
        return name;
    }

    /**
     * Установка названия роли.
     *
     * @param name Название роли
     */
    public void setName(String name) {
        this.name = name;
    }
}

# Currency Exchange System

## Описание
Этот проект представляет собой веб-систему для автоматизации обмена валют на основе Spring Boot и Java. Система позволяет пользователям выполнять операции обмена валют, отслеживать свои балансы и транзакции, а также управлять курсами валют. Проект включает возможность регистрации пользователей с различными ролями (например, администратор и пользователь), а также хранения данных о пользователях и их балансе в базе данных.

## Особенности
- Регистрация и авторизация пользователей с возможностью управления ролями (Администратор, Пользователь).
- Обмен валют с учетом актуальных курсов.
- Просмотр баланса пользователя по различным валютам.
- Хранение данных о пользователях, их балансе, транзакциях и курсах валют в базе данных.

## Технологии
- **Java 17**
- **Spring Boot** для создания веб-приложения.
- **Spring Security** для авторизации и аутентификации пользователей.
- **JPA (Java Persistence API)** для работы с базой данных.

## Роли пользователей
- **ADMIN**: имеет доступ ко всем функциям системы, включая управление курсами валют и управление пользователями.
- **USER**: может обменивать валюту и просматривать свой баланс.

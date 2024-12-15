package com.example.currencyexchange.repository;

import com.example.currencyexchange.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью {@link Role}.
 * Обеспечивает доступ к данным о ролях пользователей.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Находит роль по ее имени.
     *
     * @param name Имя роли
     * @return Объект Optional, содержащий роль, если она найдена, или пустой объект, если не найдена
     */
    Optional<Role> findByName(String name); // Поиск роли по имени
}

package ru.geekbrains.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.market.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}

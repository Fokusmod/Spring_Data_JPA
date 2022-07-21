package ru.geekbrains.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.market.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long> {

    Optional<User> findByUsername(String username);
}

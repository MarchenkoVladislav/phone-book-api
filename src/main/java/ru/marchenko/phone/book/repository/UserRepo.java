package ru.marchenko.phone.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.marchenko.phone.book.model.User;

import java.util.List;

/**
 * @author Created by Vladislav Marchenko on 31.03.2021
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findAllByNameContains(String name);
}

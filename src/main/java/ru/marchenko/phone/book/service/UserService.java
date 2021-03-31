package ru.marchenko.phone.book.service;

import ru.marchenko.phone.book.model.User;

import java.util.List;

/**
 * @author Created by Vladislav Marchenko on 31.03.2021
 */
public interface UserService extends CrudService<User> {

    List<User> getByName(String name);
}

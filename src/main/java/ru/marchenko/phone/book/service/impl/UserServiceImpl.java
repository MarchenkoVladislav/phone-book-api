package ru.marchenko.phone.book.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marchenko.phone.book.model.User;
import ru.marchenko.phone.book.repository.UserRepo;
import ru.marchenko.phone.book.service.UserService;

import java.util.List;

/**
 * @author Created by Vladislav Marchenko on 31.03.2021
 */
@Service
public class UserServiceImpl extends AbstractCrudService<UserRepo, User> implements UserService {
    @Autowired
    public UserServiceImpl(UserRepo repository) {
        super(repository);
    }

    @Override
    public List<User> getByName(String name) {
        return repository.findAllByNameContains(name);
    }
}

package ru.marchenko.phone.book.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marchenko.phone.book.model.User;
import ru.marchenko.phone.book.repository.UserRepo;
import ru.marchenko.phone.book.service.UserService;

import java.util.Date;
import java.util.List;

/**
 * @author Created by Vladislav Marchenko on 31.03.2021
 */
@Service
@Slf4j
public class UserServiceImpl extends AbstractCrudService<UserRepo, User> implements UserService {
    @Autowired
    public UserServiceImpl(UserRepo repository) {
        super(repository, User.class);
    }

    @Override
    public List<User> getByName(String name) {
        log.info(new Date() + ": getting of users by name={" + name + "}");
        return repository.findAllByNameContains(name);
    }
}

package ru.marchenko.phone.book.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import ru.marchenko.phone.book.model.User;
import ru.marchenko.phone.book.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Created by Vladislav Marchenko on 02.04.2021
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
class UserServiceImplTest {

    private final static String FIRST_USER_NAME = "Anton";
    private final static String SECOND_USER_NAME = "Andrey";
    private final static String THIRD_USER_NAME = "Alexey";
    private final static User FIRST_USER = new User(FIRST_USER_NAME);
    private final static User SECOND_USER = new User(SECOND_USER_NAME);
    private final static User THIRD_USER = new User(THIRD_USER_NAME);
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @AfterEach
    void cleanUp() {
        userService
                .getAll()
                .forEach(it -> userService.deleteById(it.getId()));

        FIRST_USER.setName(FIRST_USER_NAME);
        FIRST_USER.setId(null);

        SECOND_USER.setName(SECOND_USER_NAME);
        SECOND_USER.setId(null);

        THIRD_USER.setName(THIRD_USER_NAME);
        THIRD_USER.setId(null);
    }

    @Test
    void testGetMethods() {
        userService.save(FIRST_USER);
        userService.save(SECOND_USER);
        userService.save(THIRD_USER);

        assertEquals(3, userService.getAll().size());

        assertNotNull(userService.getById(1L));
        assertNotNull(userService.getById(2L));
        assertNotNull(userService.getById(3L));

        assertNotEquals(0, userService.getByName(FIRST_USER_NAME).size());
        assertNotEquals(0, userService.getByName(SECOND_USER_NAME).size());
        assertNotEquals(0, userService.getByName(THIRD_USER_NAME).size());
    }

    @Test
    void testSaveMethod() {
        userService.save(FIRST_USER);
        assertNotEquals((Long) 0L, FIRST_USER.getId());
        assertEquals(FIRST_USER_NAME, userService.getById(FIRST_USER.getId()).getName());
    }

    @Test
    void testUpdateMethod() {
        userService.save(FIRST_USER);
        assertNotEquals((Long) 0L, FIRST_USER.getId());
        assertEquals(FIRST_USER_NAME, userService.getById(FIRST_USER.getId()).getName());

        FIRST_USER.setName(SECOND_USER_NAME);
        userService.update(FIRST_USER);
        assertEquals(SECOND_USER_NAME, userService.getById(FIRST_USER.getId()).getName());
    }

    @Test
    void testDeleteMethod() {
        userService.save(FIRST_USER);

        assertTrue(userService.getAll().contains(FIRST_USER));

        userService.deleteById(FIRST_USER.getId());

        assertFalse(userService.getAll().contains(FIRST_USER));
    }
}
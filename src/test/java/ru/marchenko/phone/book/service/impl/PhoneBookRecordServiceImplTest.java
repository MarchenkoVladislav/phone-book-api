package ru.marchenko.phone.book.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import ru.marchenko.phone.book.model.PhoneBookRecord;
import ru.marchenko.phone.book.model.User;
import ru.marchenko.phone.book.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Created by Vladislav Marchenko on 02.04.2021
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
class PhoneBookRecordServiceImplTest {

    private final static String FIRST_USER_NAME = "Anton";
    private final static String SECOND_USER_NAME = "Andrey";
    private final static String THIRD_USER_NAME = "Alexey";
    private final static User FIRST_USER = new User(FIRST_USER_NAME);
    private final static User SECOND_USER = new User(SECOND_USER_NAME);
    private final static User THIRD_USER = new User(THIRD_USER_NAME);
    private final static Long FIRST_RECORD_OWNER_ID = 2L;
    private final static String FIRST_RECORD_TITLE = "mom";
    private final static String FIRST_RECORD_PHONE = "8-800-555-35-35";
    private final static PhoneBookRecord FIRST_PHONE_RECORD = new PhoneBookRecord(
            FIRST_RECORD_OWNER_ID, FIRST_RECORD_TITLE, FIRST_RECORD_PHONE
    );
    private final static Long SECOND_RECORD_OWNER_ID = 3L;
    private final static String SECOND_RECORD_TITLE = "dad";
    private final static String SECOND_RECORD_PHONE = "8-800-555-35-32";
    private final static PhoneBookRecord SECOND_PHONE_RECORD = new PhoneBookRecord(
            SECOND_RECORD_OWNER_ID, SECOND_RECORD_TITLE, SECOND_RECORD_PHONE
    );
    private final static Long THIRD_RECORD_OWNER_ID = 3L;
    private final static String THIRD_RECORD_TITLE = "gg";
    private final static String THIRD_RECORD_PHONE = "8-800-567-35-35";
    private final static PhoneBookRecord THIRD_PHONE_RECORD = new PhoneBookRecord(
            THIRD_RECORD_OWNER_ID, THIRD_RECORD_TITLE, THIRD_RECORD_PHONE
    );
    private final static Long FOURTH_RECORD_OWNER_ID = 4L;
    private final static String FOURTH_RECORD_TITLE = "granny";
    private final static String FOURTH_RECORD_PHONE = "8-800-555-35-31";
    private final static PhoneBookRecord FOURTH_PHONE_RECORD = new PhoneBookRecord(
            FOURTH_RECORD_OWNER_ID, FOURTH_RECORD_TITLE, FOURTH_RECORD_PHONE
    );
    @Autowired
    @Qualifier("phoneBookRecordServiceImpl")
    private PhoneBookRecordServiceImpl phoneBookService;
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService.save(FIRST_USER);
        userService.save(SECOND_USER);
        userService.save(THIRD_USER);
    }

    @AfterEach
    void cleanUp() {
        phoneBookService
                .getAll()
                .forEach(it -> phoneBookService.deleteById(it.getId()));

        FIRST_PHONE_RECORD.setTitle(FIRST_RECORD_TITLE);
        FIRST_PHONE_RECORD.setId(null);

        SECOND_PHONE_RECORD.setTitle(SECOND_RECORD_TITLE);
        SECOND_PHONE_RECORD.setId(null);

        THIRD_PHONE_RECORD.setTitle(THIRD_RECORD_TITLE);
        THIRD_PHONE_RECORD.setId(null);

        FOURTH_PHONE_RECORD.setTitle(FOURTH_RECORD_TITLE);
        FOURTH_PHONE_RECORD.setId(null);

        userService
                .getAll()
                .forEach(it -> userService.deleteById(it.getId()));

        FIRST_USER.setId(null);
        SECOND_USER.setId(null);
        THIRD_USER.setId(null);
    }

    @Test
    void testGetMethods() {
        FIRST_PHONE_RECORD.setOwnerId(FIRST_USER.getId());
        SECOND_PHONE_RECORD.setOwnerId(SECOND_USER.getId());
        THIRD_PHONE_RECORD.setOwnerId(SECOND_USER.getId());
        FOURTH_PHONE_RECORD.setOwnerId(THIRD_USER.getId());

        phoneBookService.save(FIRST_PHONE_RECORD);
        phoneBookService.save(SECOND_PHONE_RECORD);
        phoneBookService.save(THIRD_PHONE_RECORD);
        phoneBookService.save(FOURTH_PHONE_RECORD);

        assertEquals(4, phoneBookService.getAll().size());

        assertNotNull(phoneBookService.getById(1L));
        assertNotNull(phoneBookService.getById(2L));
        assertNotNull(phoneBookService.getById(3L));

        assertEquals(1, phoneBookService.getAllRecordsByOwnerId(FIRST_USER.getId()).size());
        assertEquals(2, phoneBookService.getAllRecordsByOwnerId(SECOND_USER.getId()).size());
        assertEquals(1, phoneBookService.getAllRecordsByOwnerId(THIRD_USER.getId()).size());

        assertEquals(1, phoneBookService.getAllRecordsByOwnerIdAndTitle(SECOND_USER.getId(), SECOND_RECORD_TITLE).size());

        assertEquals(1, phoneBookService.getAllRecordsByOwnerIdAndPhoneNumber(SECOND_USER.getId(), SECOND_RECORD_PHONE).size());
    }

    @Test
    void testSaveMethod() {
        FIRST_PHONE_RECORD.setOwnerId(FIRST_USER.getId());
        phoneBookService.save(FIRST_PHONE_RECORD);
        assertNotEquals((Long) 0L, FIRST_PHONE_RECORD.getId());
        assertEquals(FIRST_USER.getId(), phoneBookService.getById(FIRST_PHONE_RECORD.getId()).getOwnerId());
        assertEquals(FIRST_RECORD_TITLE, phoneBookService.getById(FIRST_PHONE_RECORD.getId()).getTitle());
        assertEquals(FIRST_RECORD_PHONE, phoneBookService.getById(FIRST_PHONE_RECORD.getId()).getPhoneNumber());
    }

    @Test
    void testDeleteMethod() {
        FIRST_PHONE_RECORD.setOwnerId(FIRST_USER.getId());
        phoneBookService.save(FIRST_PHONE_RECORD);

        assertTrue(phoneBookService.getAll().contains(FIRST_PHONE_RECORD));

        phoneBookService.deleteById(FIRST_PHONE_RECORD.getId());

        assertFalse(phoneBookService.getAll().contains(FIRST_PHONE_RECORD));
    }
}
package ru.marchenko.phone.book.service;

import ru.marchenko.phone.book.model.PhoneBookRecord;

import java.util.List;

/**
 * @author Created by Vladislav Marchenko on 31.03.2021
 */
public interface PhoneBookRecordService extends CrudService<PhoneBookRecord> {
    List<PhoneBookRecord> getAllRecordsByOwnerId(Long ownerId);

    List<PhoneBookRecord> getAllRecordsByOwnerIdAndTitle(Long ownerId, String title);

    List<PhoneBookRecord> getAllRecordsByOwnerIdAndPhoneNumber(Long ownerId, String phoneNumber);

}

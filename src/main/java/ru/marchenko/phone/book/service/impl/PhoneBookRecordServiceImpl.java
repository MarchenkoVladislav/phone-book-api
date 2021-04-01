package ru.marchenko.phone.book.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marchenko.phone.book.model.PhoneBookRecord;
import ru.marchenko.phone.book.repository.PhoneBookRecordRepo;
import ru.marchenko.phone.book.service.PhoneBookRecordService;

import java.util.List;

/**
 * @author Created by Vladislav Marchenko on 31.03.2021
 */
@Service
public class PhoneBookRecordServiceImpl extends AbstractCrudService<PhoneBookRecordRepo, PhoneBookRecord> implements PhoneBookRecordService {
    @Autowired
    public PhoneBookRecordServiceImpl(PhoneBookRecordRepo repository) {
        super(repository);
    }

    @Override
    public List<PhoneBookRecord> getAllRecordsByOwnerId(Long ownerId) {
        return repository.findAllByOwnerId(ownerId);
    }

    @Override
    public List<PhoneBookRecord> getAllRecordsByOwnerIdAndTitle(Long ownerId, String title) {
        return repository.findAllByOwnerIdAndTitle(ownerId, title);
    }

    @Override
    public List<PhoneBookRecord> getAllRecordsByOwnerIdAndPhoneNumber(Long ownerId, String phoneNumber) {
        return repository.findAllByOwnerIdAndPhoneNumber(ownerId, phoneNumber);
    }
}

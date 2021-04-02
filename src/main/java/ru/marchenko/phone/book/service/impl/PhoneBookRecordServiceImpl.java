package ru.marchenko.phone.book.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marchenko.phone.book.model.PhoneBookRecord;
import ru.marchenko.phone.book.repository.PhoneBookRecordRepo;
import ru.marchenko.phone.book.service.PhoneBookRecordService;

import java.util.Date;
import java.util.List;

/**
 * @author Created by Vladislav Marchenko on 31.03.2021
 */
@Service("phoneBookRecordServiceImpl")
@Slf4j
public class PhoneBookRecordServiceImpl extends AbstractCrudService<PhoneBookRecordRepo, PhoneBookRecord> implements PhoneBookRecordService {
    @Autowired
    public PhoneBookRecordServiceImpl(PhoneBookRecordRepo repository) {
        super(repository, PhoneBookRecord.class);
    }

    @Override
    public List<PhoneBookRecord> getAllRecordsByOwnerId(Long ownerId) {
        log.info(new Date() + ": getting of phone book records by ownerId={" + ownerId + "}");
        return repository.findAllByOwnerId(ownerId);
    }

    @Override
    public List<PhoneBookRecord> getAllRecordsByOwnerIdAndTitle(Long ownerId, String title) {
        log.info(new Date() + ": getting of phone book records by ownerId={" + ownerId + "} and title={" + title + "}");
        return repository.findAllByOwnerIdAndTitle(ownerId, title);
    }

    @Override
    public List<PhoneBookRecord> getAllRecordsByOwnerIdAndPhoneNumber(Long ownerId, String phoneNumber) {
        log.info(new Date() + ": getting of phone book records by ownerId={" + ownerId + "} and phoneNumber={" + phoneNumber + "}");
        return repository.findAllByOwnerIdAndPhoneNumber(ownerId, phoneNumber);
    }
}

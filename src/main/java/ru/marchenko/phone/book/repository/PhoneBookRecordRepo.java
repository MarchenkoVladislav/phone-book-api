package ru.marchenko.phone.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.marchenko.phone.book.model.PhoneBookRecord;

import java.util.List;

/**
 * @author Created by Vladislav Marchenko on 31.03.2021
 */
@Repository
public interface PhoneBookRecordRepo extends JpaRepository<PhoneBookRecord, Long> {
    List<PhoneBookRecord> findAllByOwnerId(Long ownerId);

    List<PhoneBookRecord> findAllByOwnerIdAndTitle(Long ownerId, String title);

    List<PhoneBookRecord> findAllByOwnerIdAndPhoneNumber(Long ownerId, String phoneNumber);
}

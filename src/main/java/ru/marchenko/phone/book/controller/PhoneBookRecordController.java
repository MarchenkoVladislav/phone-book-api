package ru.marchenko.phone.book.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.marchenko.phone.book.model.PhoneBookRecord;
import ru.marchenko.phone.book.model.User;
import ru.marchenko.phone.book.service.PhoneBookRecordService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Created by Vladislav Marchenko on 01.04.2021
 */
@RestController
@RequestMapping("/api/records")
public class PhoneBookRecordController {

    private final PhoneBookRecordService phoneBookRecordService;

    public PhoneBookRecordController(PhoneBookRecordService phoneBookRecordService) {
        this.phoneBookRecordService = phoneBookRecordService;
    }


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhoneBookRecord>> getAllRecords() {
        List<PhoneBookRecord> records = phoneBookRecordService.getAll();

        if (records.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneBookRecord> getRecordById(@PathVariable Long id) {
        PhoneBookRecord record = phoneBookRecordService.getById(id);

        if (record == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneBookRecord> saveRecord(@RequestBody @Valid PhoneBookRecord record) {
        if (record == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        PhoneBookRecord savedRecord = phoneBookRecordService.save(record);

        if (savedRecord == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneBookRecord> updateRecord(@RequestBody @Valid PhoneBookRecord record) {
        if (record == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (phoneBookRecordService.getById(record.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PhoneBookRecord updatedRecord = phoneBookRecordService.update(record);

        if (updatedRecord == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteRecordById(@PathVariable Long id) {

        if (phoneBookRecordService.getById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        phoneBookRecordService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/ownerId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhoneBookRecord>> getAllRecordsByOwnerId(@RequestParam Long ownerId) {
        if (ownerId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<PhoneBookRecord> records = phoneBookRecordService.getAllRecordsByOwnerId(ownerId);

        if (records.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(records, HttpStatus.OK);
    }


    @RequestMapping(value = "/ownerIdAndTitle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhoneBookRecord>> getAllRecordsByOwnerIdAndTitle(@RequestParam Long ownerId, @RequestParam String title) {
        if (ownerId == null || title == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<PhoneBookRecord> records = phoneBookRecordService.getAllRecordsByOwnerIdAndTitle(ownerId, title);

        if (records.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @RequestMapping(value = "/ownerIdAndNumber", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhoneBookRecord>> getAllRecordsByOwnerIdAndPhoneNumber(@RequestParam Long ownerId, @RequestParam String phoneNumber) {
        if (ownerId == null || phoneNumber == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<PhoneBookRecord> records = phoneBookRecordService.getAllRecordsByOwnerIdAndPhoneNumber(ownerId, phoneNumber);

        if (records.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(records, HttpStatus.OK);
    }


}

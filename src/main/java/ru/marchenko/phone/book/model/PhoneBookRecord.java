package ru.marchenko.phone.book.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Created by Vladislav Marchenko on 31.03.2021
 */
@Entity
@Table(name = "phone_book_records")
@Data
@NoArgsConstructor
public class PhoneBookRecord extends BaseEntity {

    public PhoneBookRecord(Long ownerId, String title, String phoneNumber) {
        this.ownerId = ownerId;
        this.title = title;
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "owner_id", nullable = false, columnDefinition = "bigint")
    private Long ownerId;

    @Column(name = "title", nullable = false, columnDefinition = "character varying(100)")
    private String title;

    @Column(name = "phone_number", nullable = false, columnDefinition = "character varying(18)")
    private String phoneNumber;
}

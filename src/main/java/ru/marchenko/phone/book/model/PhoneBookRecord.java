package ru.marchenko.phone.book.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Created by Vladislav Marchenko on 31.03.2021
 */
@Entity
@Table(name = "phone_book_records")
@Setter
@Getter
public class PhoneBookRecord extends BaseEntity {
    @Column(name = "owner_id", nullable = false, columnDefinition = "bigint")
    private Long ownerId;

    @Column(name = "title", nullable = false, columnDefinition = "character varying(100)")
    private String title;

    @Column(name = "phone_number", nullable = false, columnDefinition = "character varying(18)")
    private String phoneNumber;
}
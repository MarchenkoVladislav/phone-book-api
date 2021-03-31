package ru.marchenko.phone.book.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Created by Vladislav Marchenko on 31.03.2021
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(name = "name", nullable = false, columnDefinition = "character varying(100)")
    private String name;

    @OneToMany(
            mappedBy = "ownerId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private Set<PhoneBookRecord> phoneBookRecords = new HashSet<>();
}

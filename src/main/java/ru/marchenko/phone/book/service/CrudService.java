package ru.marchenko.phone.book.service;

import ru.marchenko.phone.book.model.BaseEntity;

import java.util.List;

/**
 * @author Created by Vladislav Marchenko on 31.03.2021
 */
public interface CrudService<E extends BaseEntity> {
    List<E> getAll();

    E getById(Long id);

    E saveOrUpdate(E entity);

    void deleteById(Long id);
}

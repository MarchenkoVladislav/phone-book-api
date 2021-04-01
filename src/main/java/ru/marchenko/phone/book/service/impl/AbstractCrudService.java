package ru.marchenko.phone.book.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.marchenko.phone.book.model.BaseEntity;
import ru.marchenko.phone.book.service.CrudService;

import java.util.List;

/**
 * @author Created by Vladislav Marchenko on 31.03.2021
 */
@AllArgsConstructor
abstract class AbstractCrudService<R extends JpaRepository<E, Long>, E extends BaseEntity> implements CrudService<E> {

    protected R repository;

    @Override
    public List<E> getAll() {
        return repository.findAll();
    }

    @Override
    public E getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public E saveOrUpdate(E entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

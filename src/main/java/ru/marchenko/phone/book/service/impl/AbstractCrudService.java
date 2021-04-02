package ru.marchenko.phone.book.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.marchenko.phone.book.model.BaseEntity;
import ru.marchenko.phone.book.service.CrudService;

import java.util.Date;
import java.util.List;

/**
 * @author Created by Vladislav Marchenko on 31.03.2021
 */
@AllArgsConstructor
@Slf4j
abstract class AbstractCrudService<R extends JpaRepository<E, Long>, E extends BaseEntity> implements CrudService<E> {

    protected R repository;

    private final Class<E> typeOfE;

    @Override
    public List<E> getAll() {
        log.info(new Date() + ": getting of all entities of " + typeOfE);
        return repository.findAll();
    }

    @Override
    @Caching(cacheable = {
            @Cacheable(value = "users", key = "#id"),
            @Cacheable(value = "phoneBookRecords", key = "#id")
    })
    public E getById(Long id) {
        log.info(new Date() + ": getting of entity of " + typeOfE + " by id={" + id + "}");
        return repository.getById(id);
    }

    @Override
    @Caching(cacheable = {
            @Cacheable(value = "users", key = "#entity.id"),
            @Cacheable(value = "phoneBookRecords", key = "#entity.id")
    })
    public E save(E entity) {
        log.info(new Date() + ": saving of entity of " + typeOfE + " - " + entity);
        return repository.save(entity);
    }

    @Override
    @Caching(put = {
            @CachePut(value = "users", key = "#entity.id"),
            @CachePut(value = "phoneBookRecords", key = "#entity.id")
    })
    public E update(E entity) {
        log.info(new Date() + ": updating of entity of " + typeOfE + " - " + entity);
        return repository.save(entity);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "users", key = "#id"),
            @CacheEvict(value = "phoneBookRecords", key = "#id")
    })
    public void deleteById(Long id) {
        log.info(new Date() + ": deleting of entity of " + typeOfE + " by id={" + id + "}");
        repository.deleteById(id);
    }
}

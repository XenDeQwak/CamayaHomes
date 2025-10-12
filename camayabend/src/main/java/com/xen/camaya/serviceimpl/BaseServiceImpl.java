package com.xen.camaya.serviceimpl;

import com.xen.camaya.service.CrudService;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<M, E, ID> implements CrudService<M, ID> {

    protected final CrudRepository<E, ID> repository;

    protected BaseServiceImpl(CrudRepository<E, ID> repository) {
        this.repository = repository;
    }

    protected abstract E toEntity(M model);
    protected abstract M toModel(E entity);

    @Override
    public M create(M model) {
        E saved = repository.save(toEntity(model));
        return toModel(saved);
    }

    @Override
    public List<M> getAll() {
        List<M> models = new ArrayList<>();
        Iterable<E> entities = repository.findAll();
        for (E entity : entities) {
            models.add(toModel(entity));
        }
        return models;
    }

    @Override
    public M get(ID id) {
        Optional<E> entity = repository.findById(id);
        return entity.map(this::toModel).orElse(null);
    }

    @Override
    public M update(M model) {
        E saved = repository.save(toEntity(model));
        return toModel(saved);
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }
}

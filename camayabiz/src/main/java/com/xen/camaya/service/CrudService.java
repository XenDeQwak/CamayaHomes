package com.xen.camaya.service;

import java.util.List;

public interface CrudService<T, ID> {
    T create(T entity);
    T update(T entity);
    T get(ID id);
    List<T> getAll();
    void delete(ID id);
    boolean assign(Integer userId, Integer adminId);
}

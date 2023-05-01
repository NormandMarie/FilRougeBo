package com.m2i.filrougebo.dao;

import java.util.List;

public interface GenericDao<T, ID> {

    T create(T entity);

    List<T> findAll();

    T findById(ID id);

    boolean update(T entity);

    boolean delete(T entity);

}


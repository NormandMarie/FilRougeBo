package com.m2i.filrougebo.dao;

import java.util.List;

public class ProductDao implements IntProductDao{
    @Override
    public boolean create(Object entity) {
        return false;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Object findById(Object o) {
        return null;
    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Object entity) {

    }
}

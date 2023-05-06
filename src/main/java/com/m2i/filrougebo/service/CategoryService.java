package com.m2i.filrougebo.service;

import com.m2i.filrougebo.dao.CategoryDao;
import com.m2i.filrougebo.dao.IntCategoryDao;
import com.m2i.filrougebo.entity.Category;

import java.util.List;

public class CategoryService {
    private IntCategoryDao categoryDao;

    public CategoryService() {
        this.categoryDao = new CategoryDao();
    }
    public CategoryService(IntCategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public Category create(Category category){
        return categoryDao.create(category);
    }
    public Category create(String name){
        Category cat = new Category(name);
        return categoryDao.create(cat);
    }
    public Category create1(String name){
        Category cat = new Category(name);
        return categoryDao.create(cat);
    }

    public List<Category> findAll(){
        return categoryDao.findAll();
    }
    public Category findById(int id){
        return categoryDao.findById(id);
    }
    public boolean update(Category category){
        return categoryDao.update(category);
    }
    public boolean delete(Category category){
        return categoryDao.delete(category);
    }
}

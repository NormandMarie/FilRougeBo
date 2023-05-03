package com.m2i.filrougebo.service;

import com.m2i.filrougebo.dao.CategoryDao;
import com.m2i.filrougebo.dao.IntCategoryDao;
import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private static IntCategoryDao categoryDao = new CategoryDao();

    public Category create(Category category){
        return categoryDao.create(category);
    }
    public Category create(String name){
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

    public List<Category> findAllCategoriesExceptProductCategory(Product product) {
        List<Category> categoryList = new ArrayList<>();
        for (Category category: findAll()) {
            if (category.getIdCategory() != product.getCategory().getIdCategory()) {
                categoryList.add(category);
            }
        }
        return categoryList;
    }

}

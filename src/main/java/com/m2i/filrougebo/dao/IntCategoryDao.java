package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.entity.Category;

import java.util.List;

public interface IntCategoryDao extends GenericDao<Category, Integer>{

    List<Category> searchByName(String search);

}

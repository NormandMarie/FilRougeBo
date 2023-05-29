package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.entity.Product;

import java.util.List;

public interface IntProductDao extends GenericDao<Product,Integer>{

    List<Product> searchProductPerNameOrDescriptionOrCategoryName(String search);

}

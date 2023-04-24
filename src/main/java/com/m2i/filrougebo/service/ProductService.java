package com.m2i.filrougebo.service;

import com.m2i.filrougebo.dao.IntProductDao;
import com.m2i.filrougebo.dao.ProductDao;
import com.m2i.filrougebo.entity.Product;

import java.util.List;

public class ProductService {
    private IntProductDao productDao = new ProductDao();

    public List<Product> findAll() { return productDao.findAll();}

}

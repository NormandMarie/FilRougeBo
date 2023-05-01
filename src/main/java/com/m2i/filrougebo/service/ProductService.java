package com.m2i.filrougebo.service;

import com.m2i.filrougebo.dao.IntProductDao;
import com.m2i.filrougebo.dao.ProductDao;
import com.m2i.filrougebo.entity.Product;

import java.util.List;

public class ProductService {
    private IntProductDao productDao = new ProductDao();

    public List<Product> findAll() { return productDao.findAll();}

    public void createProduct(Product product) {
        productDao.create(product);
    }

    public void updateProduct(Product product) {
        productDao.update(product);
    }

    public void deleteProduct(Product product) {
        productDao.delete(product);
    }

}

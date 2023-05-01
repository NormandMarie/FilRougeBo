package com.m2i.filrougebo.service;

import com.m2i.filrougebo.dao.IntProductDao;
import com.m2i.filrougebo.dao.ProductDao;
import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.entity.Product;
import com.m2i.filrougebo.enums.Month;

import java.util.List;

public class ProductService {
    private IntProductDao productDao = new ProductDao();

    public Product findById(int id) {
        return productDao.findById(id);
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }


    public Product createProduct(String name, String unit, double pricePerUnit, String imgUrl, double vat,
                              String description, double stock, Category category, List<Month> seasonalMonths) {

        Product product = new Product(name, unit, pricePerUnit, imgUrl, vat, description, stock, category, seasonalMonths);

        return productDao.create(product);
    }

    public boolean updateProduct(int id, String name, String unit, double pricePerUnit, String imgUrl, double vat,
                                 String description, double stock, Category category, List<Month> seasonalMonths) {

        Product product = new Product(id, name, unit, pricePerUnit, imgUrl, vat, description, stock, category, seasonalMonths);

        return productDao.update(product);
    }

    public void deleteProduct(Product product) {
        productDao.delete(product);
    }

}

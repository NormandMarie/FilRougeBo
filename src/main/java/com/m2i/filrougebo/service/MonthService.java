package com.m2i.filrougebo.service;

import com.m2i.filrougebo.dao.ProductMonthsDao;
import com.m2i.filrougebo.enums.Month;

import java.util.List;

public class MonthService {

    private ProductMonthsDao productMonthsDao = new ProductMonthsDao();

    public List<Month> findAll() {
        return productMonthsDao.findAll();
    }

}

package com.m2i.filrougebo.service;

import com.m2i.filrougebo.dao.ProductMonthsDao;
import com.m2i.filrougebo.enums.Month;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MonthService {

    private ProductMonthsDao productMonthsDao = new ProductMonthsDao();

    public List<Month> findAll() {
        return productMonthsDao.findAll();
    }

    public List<Month> findAllAndSort() {
        List<Month> months = productMonthsDao.findAll();
        List<Month> monthList = months.stream()
                .sorted(Comparator.comparing(Month::getId))
                .collect(Collectors.toList());
        return monthList;
    }


}

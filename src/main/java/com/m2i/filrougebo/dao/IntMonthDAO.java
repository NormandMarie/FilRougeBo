package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.enums.Month;

import java.util.List;

public interface IntMonthDAO extends GenericDao<Month, Integer>{

    List<Month> findAllMonthsPerProduct(int id);

}

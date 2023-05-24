package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.entity.Admin;

public interface IntAdminDao extends GenericDao<Admin, Integer>{
    Admin authenticate(String username, String password);
    Admin findByUsername(String username);

}

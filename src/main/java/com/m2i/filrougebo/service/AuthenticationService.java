package com.m2i.filrougebo.service;


import com.m2i.filrougebo.dao.AdminDao;
import com.m2i.filrougebo.dao.DataBase;
import com.m2i.filrougebo.entity.Admin;

import java.sql.*;

public class AuthenticationService {
    private AdminDao adminDao;

    public AuthenticationService() {
        this.adminDao = new AdminDao();
    }

    public boolean authenticate(String username, String password) {
        Admin user = adminDao.authenticate(username, password);
        return user != null;
    }

    public  boolean isSuperAdmin(String username) {
        Admin admin = adminDao.findByUsername(username);
        return admin != null && admin.getIsSuperAdmin();
    }

    public Admin authenticatewithSuper(String username, String password) {
        return adminDao.authenticatewithSuper(username, password);
    }
}








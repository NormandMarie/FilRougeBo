package com.m2i.filrougebo.service;

import com.m2i.filrougebo.Dto.AdminDto;
import com.m2i.filrougebo.dao.AdminDao;
import com.m2i.filrougebo.dao.IntAdminDao;
import com.m2i.filrougebo.entity.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private static IntAdminDao adminDao = new AdminDao();

    public Admin create(Admin admin){
        return adminDao.create(admin);
    }
    public List<Admin> findAll(){
        return adminDao.findAll();
    }

    public List<AdminDto> getAllAdmins() {
        List<Admin> admins = adminDao.findAll();
        List<AdminDto> adminDTOs = new ArrayList<>();

        for (Admin admin : admins) {
            AdminDto adminDTO = new AdminDto(admin.getIdAdmin(), admin.getUsername(), admin.getIsSuperAdmin());
            adminDTOs.add(adminDTO);
        }

        return adminDTOs;
    }

    public Admin findById(int id){
        return adminDao.findById(id);
    }
    public boolean update(Admin admin){
        return adminDao.update(admin);
    }
    public boolean delete(Admin admin){
        return adminDao.delete(admin);
    }

}

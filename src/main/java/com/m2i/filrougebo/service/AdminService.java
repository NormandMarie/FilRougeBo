package com.m2i.filrougebo.service;

import com.m2i.filrougebo.dto.AdminDto;
import com.m2i.filrougebo.dao.AdminDao;
import com.m2i.filrougebo.dao.IntAdminDao;
import com.m2i.filrougebo.entity.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private IntAdminDao adminDao;

    public AdminService() {
        adminDao = new AdminDao();
    }
    public AdminService(IntAdminDao adminDao) {
        this.adminDao = adminDao;
    }

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
            AdminDto adminDTO = new AdminDto(
                    admin.getIdAdmin(),
                    admin.getUsername(),
                    admin.getIsSuperAdmin(),
                    admin.getFirstName(),
                    admin.getLastName(),
                    admin.getEmail());
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

    public AdminDto getAdminDtoById(int id) {
        Admin admin = adminDao.findById(id);
        if (admin == null) {
            return null;
        }
        return new AdminDto(
                admin.getIdAdmin(),
                admin.getUsername(),
                admin.getIsSuperAdmin(),
                admin.getFirstName(),
                admin.getLastName(),
                admin.getEmail());
    }

}

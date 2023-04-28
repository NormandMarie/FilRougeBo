package com.m2i.filrougebo.service;

import com.m2i.filrougebo.Dto.AdminDto;
import com.m2i.filrougebo.dao.AdminDao;
import com.m2i.filrougebo.dao.IntAdminDao;
import com.m2i.filrougebo.entity.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private IntAdminDao adminDao = new AdminDao();
    public List<AdminDto> getAllAdmins() {
        List<Admin> admins = adminDao.findAll();
        List<AdminDto> adminDTOs = new ArrayList<>();

        for (Admin admin : admins) {
            AdminDto adminDTO = new AdminDto(admin.getIdAdmin(), admin.getUsername(), admin.getIsSuperAdmin());
            adminDTOs.add(adminDTO);
        }

        return adminDTOs;
    }

}

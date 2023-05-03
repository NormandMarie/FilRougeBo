package com.m2i.filrougebo.Dto;

import com.m2i.filrougebo.entity.Admin;

public class AdminDto {
        private int idAdmin;
        private String username;
        private boolean isSuperAdmin;

        public AdminDto(Admin admin) {
            this.idAdmin = admin.getIdAdmin();
            this.username = admin.getUsername();
            this.isSuperAdmin = admin.getIsSuperAdmin();
        }

    public AdminDto(int idAdmin, String username, boolean isSuperAdmin) {
        this.idAdmin = idAdmin;
        this.username = username;
        this.isSuperAdmin = isSuperAdmin;
    }

    public int getIdAdmin() {
            return idAdmin;
        }

        public void setIdAdmin(int idAdmin) {
            this.idAdmin = idAdmin;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public boolean getIsSuperAdmin() {
            return isSuperAdmin;
        }

        public void setIsSuperAdmin(boolean isSuperAdmin) {
            this.isSuperAdmin = isSuperAdmin;
        }
    }



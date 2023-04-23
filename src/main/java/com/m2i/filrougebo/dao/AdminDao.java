package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class AdminDao implements IntAdminDao{
    Connection conn = ConnectionManager.getInstance();

    @Override
    public void create(Admin entity) {
        try{
            PreparedStatement ps=conn.prepareStatement("INSERT INTO admins (username, isSuperAdmin, password) VALUES (?,?,?)");
            ps.setString(1,entity.getUsername());
            ps.setBoolean(2, entity.getIsSuperAdmin());
            ps.setString(3,entity.getPassword());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM admins");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Admin admin = new Admin(rs.getInt("id"),rs.getString("username"),rs.getBoolean("isSuperAdmin"), rs.getString("password"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admins;
    }

    @Override
    public Admin findById(Integer integer) {
        Admin admin = null;
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM admins WHERE id = ? ");
            ps.setInt(1,integer);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                admin = new Admin(rs.getInt("id"),rs.getString("username"),rs.getBoolean("isSuperAdmin"), rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }

    @Override
    public void update(Admin entity) {

    }

    @Override
    public void delete(Admin entity) {
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM admins WHERE id = ?");
            ps.setInt(1,entity.getIdAdmin());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

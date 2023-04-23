package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class AdminDao implements IntAdminDao{
    Connection conn = ConnectionManager.getInstance();

    @Override
    public void create(Admin entity) {
        try{
            PreparedStatement ps=conn.prepareStatement("INSERT INTO admin (username, isSuperAdmin, password) VALUES (?,?,?)");
            ps.setString(1,entity.getUsername());
            ps.setBoolean(2, entity.isSuperAdmin());
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM admin");
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
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM admin WHERE id = ? ");
            ps.setInt(1,integer);
            ResultSet rs = ps.executeQuery();
            Admin admin = new Admin(rs.getInt("id"),rs.getString("username"),rs.getBoolean("isSuperAdmin"), rs.getString("password"));
            return admin;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Admin entity) {

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE admin SET username=?, isSuperAdmin=?, password=? WHERE id=?");
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setBoolean(2, entity.isSuperAdmin());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setInt(4, entity.getIdAmin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Admin entity) {
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM admin WHERE id = ?");
            ps.setInt(1,entity.getIdAmin());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.entity.Admin;
import com.m2i.filrougebo.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements IntCategoryDao{
    Connection conn = ConnectionManager.getInstance();
    @Override
    public void create(Category entity) {
        try{
            PreparedStatement ps=conn.prepareStatement("INSERT INTO category (name) VALUES (?)");
            ps.setString(1,entity.getName());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM category");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Category category = new Category(rs.getInt("id"),rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }

    @Override
    public Category findById(Integer integer) {
        Category category=null;
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM category WHERE id = ? ");
            ps.setInt(1,integer);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                category = new Category(rs.getInt("id"),rs.getString("name"));
            }else{
                System.out.println("no category found");
                // Ajout d'une exception "product not found" ?
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public void update(Category entity) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE category SET name=? WHERE id=?");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getIdCategory());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category entity) {
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM category WHERE id = ?");
            ps.setInt(1,entity.getIdCategory());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

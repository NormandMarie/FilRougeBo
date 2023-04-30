package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.entity.Admin;
import com.m2i.filrougebo.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements IntCategoryDao{
    Connection conn = ConnectionManager.getInstance();

    private Category mapToCategory(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        return new Category(id,name);
    }
    @Override
    public Category create(Category entity) {

        String query = "INSERT INTO categories (name) VALUES (?)";

        try(PreparedStatement ps=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1,entity.getName());
            int row = ps.executeUpdate();
            if(row == 1){
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if(generatedKeys.next()){
                    entity.setIdCategory(generatedKeys.getInt(1));
                    return entity;
                }
            }
            throw new RuntimeException("Could not create Category entity !");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Category> findAll() {

        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Category category = mapToCategory(rs);
                categories.add(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Category findById(Integer integer) {

        String query = "SELECT * FROM categories WHERE id = ? ";

        try(PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1,integer);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Category category = mapToCategory(rs);
                return category;
            }
            throw new RuntimeException("No Category found for id = "+integer);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Category entity) {

        String query = "UPDATE categories SET name=? WHERE id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getIdCategory());
            int row = preparedStatement.executeUpdate();

            if(row == 1){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Category entity) {

        String query = "DELETE FROM categories WHERE id = ?";

        try(PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1,entity.getIdCategory());
            int row = ps.executeUpdate();
            if(row == 1){
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

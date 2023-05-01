package com.m2i.filrougebo.dao;
import com.m2i.filrougebo.entity.Admin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao implements IntAdminDao{

    Connection conn = DataBase.getInstance();

    private Admin mapToAdmin(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        boolean isSuperAdmin = resultSet.getBoolean("isSuperAdmin");
        String password = resultSet.getString("password");
        return new Admin(id,username,isSuperAdmin,password);
    }

    @Override
    public Admin create(Admin entity) {

        String query ="INSERT INTO admins (username, isSuperAdmin, password) VALUES (?,?,?)";
        try ( PreparedStatement ps=conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,entity.getUsername());
            ps.setBoolean(2, entity.getIsSuperAdmin());
            ps.setString(3,entity.getPassword());
            int row = ps.executeUpdate();
            if(row==1){
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if(generatedKeys.next()){
                    entity.setIdAmin(generatedKeys.getInt(1));
                    return entity;
                }
            }
            throw new RuntimeException("Could not create Admin entity !");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM admins";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Admin admin = mapToAdmin(rs);
                admins.add(admin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admins;
    }

    @Override
    public Admin findById(Integer integer) {

        String query = "SELECT * FROM admins WHERE id = ? ";

        try(PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1,integer);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Admin admin = mapToAdmin(rs);
                return admin;
            }
            throw new RuntimeException("No Admin found for id = "+integer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Admin entity) {
        String query = "UPDATE admins SET password = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){

            preparedStatement.setString(1, entity.getPassword());
            preparedStatement.setInt(2, entity.getIdAdmin());
            int row = preparedStatement.executeUpdate();
            if (row == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Admin entity) {
        String query = "DELETE FROM admins WHERE id = ?";

        try(PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1,entity.getIdAdmin());
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

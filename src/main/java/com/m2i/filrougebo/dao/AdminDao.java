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
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String email = resultSet.getString("email");

        return new Admin(id, username, isSuperAdmin, password, firstName, lastName, email);
    }

    @Override
    public Admin create(Admin entity) {

        String query ="INSERT INTO admins (username, isSuperAdmin, password, firstName, lastName, email) VALUES (?,?,?,?,?,?)";
        try ( PreparedStatement ps=conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1,entity.getUsername());
            ps.setBoolean(2, entity.getIsSuperAdmin());
            ps.setString(3,entity.getPassword());
            ps.setString(4,entity.getFirstName());
            ps.setString(5,entity.getLastName());
            ps.setString(6,entity.getEmail());

            int row = ps.executeUpdate();

            if (row == 1) {
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
    @Override
    public Admin authenticate(String username, String password) {
        String query = "SELECT * FROM admins WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                boolean isSuperAdmin = rs.getBoolean("isSuperAdmin");
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                return new Admin(id, username, isSuperAdmin, password, firstName, lastName, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public Admin findByUsername(String username) {
        String query = "SELECT * FROM admins WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                boolean isSuperAdmin = rs.getBoolean("isSuperAdmin");
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                return new Admin(id, username, isSuperAdmin, password, firstName, lastName, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Admin authenticatewithSuper(String username, String password) {
        String query = "SELECT * FROM admins WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                boolean isSuperAdmin = rs.getBoolean("isSuperAdmin");
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                return new Admin(id, username, isSuperAdmin, password, firstName, lastName, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

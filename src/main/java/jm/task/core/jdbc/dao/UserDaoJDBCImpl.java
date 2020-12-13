package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection con = Util.getMysqlConnection();
        Statement stat = con.createStatement();) {
            String query = "CREATE TABLE IF NOT EXISTS users " +
                    "(id bigint, name text, lastName text, age tinyint);";
            stat.execute(query);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection con = Util.getMysqlConnection();
             Statement stat = con.createStatement();) {
            String query = "DROP TABLE IF EXISTS users";
            stat.execute(query);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users(name, lastName, age) values (?, ?, ?)";
        try (Connection con = Util.getMysqlConnection();
             PreparedStatement prepStat = con.prepareStatement(query);) {
            prepStat.setByte(3, age);
            prepStat.setString(1, name);
            prepStat.setString(2, lastName);
            prepStat.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection con = Util.getMysqlConnection();
             PreparedStatement prepStat = con.prepareStatement(query);) {
            prepStat.setLong(1, id);
            prepStat.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String query = "select id, name, lastName, age from users";
        List<User> list = new ArrayList<>();
        try (Connection con = Util.getMysqlConnection();
             PreparedStatement prepStat = con.prepareStatement(query);) {
            ResultSet rs = prepStat.executeQuery();
            while (rs.next()) {
                User newUser = new User(rs.getString("name"), rs.getString("lastName"), rs.getByte("age"));
                list.add(newUser);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } finally {
            return list;
        }
    }

    public void cleanUsersTable() {
        String query = "DELETE FROM users";
        try (Connection con = Util.getMysqlConnection();
             Statement stat = con.createStatement();) {
            stat.executeUpdate(query);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}

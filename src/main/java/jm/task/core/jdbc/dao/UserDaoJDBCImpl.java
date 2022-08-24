package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl(){

    }
    public void createUsersTable() {
        try (        Connection connection = Util.getConnection();
                     Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, nameC VARCHAR(255), lastNameC VARCHAR(255), ageC INT)");
            System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users");
            System.out.println("Table Deleted Successfully");
        } catch (SQLException e) {
            System.err.println("Cannot connect ! ");
            e.printStackTrace();
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        try ( Connection connection = Util.getConnection();
              PreparedStatement prepStatement = connection.prepareStatement("INSERT users (nameC, lastNameC, ageC) VALUES (?, ?, ?)")) {
            System.out.println("Database connected");
            prepStatement.setString(1, name);
            prepStatement.setString(2, lastName);
            prepStatement.setByte(3, age);
            prepStatement.executeUpdate();
            System.out.println("Information Added Successfully");
        } catch (SQLException e) {
            System.err.println("Cannot connect ! ");
            e.printStackTrace();
        }
    }
    public void removeUserById(long id) {
        try ( Connection connection = Util.getConnection();
              Statement statement = connection.createStatement()) {
            System.out.println("Database connected");
            statement.executeUpdate("DELETE FROM users WHERE Id = " + id + ";");
            System.out.println("Information Deleted Successfully");
        } catch (SQLException e) {
            System.err.println("Cannot connect ! ");
            e.printStackTrace();
        }
    }
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try ( Connection connection = Util.getConnection();
              Statement statement = connection.createStatement()) {
            System.out.println("Database connected");
            ResultSet set = connection.createStatement().executeQuery("SELECT * FROM users");
            while (set.next()) {
                User user = new User(set.getString("nameC"), set.getString("lastNameC"), set.getByte("ageC"));
                user.setId(set.getLong("Id"));
                list.add(user);
            }
            System.out.println("Information Added To List Successfully");
        } catch (SQLException e) {
            System.err.println("Cannot connect ! ");
            e.printStackTrace();
        }
        return list;
    }
    public void cleanUsersTable() {
        try ( Connection connection = Util.getConnection();
              Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE users");
            System.out.println("Data Deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

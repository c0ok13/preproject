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
        Connection connection = Util.getConnection();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("""
                create table IF NOT EXISTS user
                (id       int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                 name     varchar(255) null,
                 lastName varchar(255) null,
                 age      tinyint      null
                 );""");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("DROP TABLE IF EXISTS USER");
        }   catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        Connection connection = Util.getConnection();

        try {
            PreparedStatement insertUser = connection.prepareStatement( "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)");
            insertUser.setString(1, name);
            insertUser.setString(2, lastName);
            insertUser.setByte(3, age);
            insertUser.executeUpdate();
            System.out.println(name + " added to databse");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();

        try {
            PreparedStatement deleteUser = connection.prepareStatement("DELETE FROM user WHERE ID = ?");
            deleteUser.setLong(1, id);
            deleteUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rst = statement.executeQuery("SELECT * FROM USER")
        ){
            while (rst.next()) {
                User user = new User();
                user.setId(rst.getLong(1));
                user.setName(rst.getString(2));
                user.setLastName(rst.getString(3));
                user.setAge(rst.getByte(4));
                users.add(user);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("TRUNCATE TABLE IF EXISTS USER");
        }   catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

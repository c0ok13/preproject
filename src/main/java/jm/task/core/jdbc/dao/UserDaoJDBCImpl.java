package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement stmt = connection.createStatement()){
            stmt.executeUpdate("""
                create table IF NOT EXISTS user
                (id       int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                 name     varchar(255) null,
                 lastName varchar(255) null,
                 age      tinyint      null
                 );""");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS user;");
            connection.commit();
        } catch (SQLException e) {
            try {
                System.out.println("rollback");
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement insertUser = connection.prepareStatement( "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)")){
            insertUser.setString(1, name);
            insertUser.setString(2, lastName);
            insertUser.setByte(3, age);
            insertUser.executeUpdate();
            System.out.println(name + " added to databse");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement deleteUser = connection.prepareStatement("DELETE FROM user WHERE ID = ?")) {
            deleteUser.setLong(1, id);
            deleteUser.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                System.err.print("Transaction is being rolled back");
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rst = stmt.executeQuery("SELECT * FROM user");
        ) {
            while (rst.next()) {
                User user = new User();
                user.setId(rst.getLong(1));
                user.setName(rst.getString(2));
                user.setLastName(rst.getString(3));
                user.setAge(rst.getByte(4));
                users.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("TRUNCATE TABLE user");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}

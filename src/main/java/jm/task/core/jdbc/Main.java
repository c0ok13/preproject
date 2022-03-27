package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class Main {

    static final String DB_URL = "jdbc:mysql://localhost:3306/preproject";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args)
    {try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement()
    ) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        System.out.println("Created table in given database...");

        userService.saveUser("Yaroslav", "Mimeev", (byte) 22);
        userService.saveUser("Ivan", "Ivanov", (byte) 39);
        userService.saveUser("Arthas", "Menethil", (byte) 24);
        userService.saveUser("Vladimir", "Putin", (byte) 69);

        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
}

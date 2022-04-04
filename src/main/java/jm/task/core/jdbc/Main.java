package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    static final String DB_URL = "jdbc:mysql://localhost:3306/preproject";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args)
    {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        System.out.println("Created table in given database...");

        userService.saveUser("Yaroslav", "Mimeev", (byte) 22);
        userService.saveUser("Ivan", "Ivanov", (byte) 39);
        userService.saveUser("Arthas", "Menethil", (byte) 24);
        userService.saveUser("Vladimir", "Putin", (byte) 69);

        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}

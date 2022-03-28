package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {

    public static void main(String[] args)
    {
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
    }
}

package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
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
    {
        //UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
//        System.out.println("Created table in given database...");
//
        userDaoHibernate.saveUser("Yaroslav", "Mimeev", (byte) 22);
        userDaoHibernate.saveUser("Yaroslav", "Mimeev", (byte) 22);
        userDaoHibernate.saveUser("Ivan", "Ivanov", (byte) 39);
        userDaoHibernate.saveUser("Arthas", "Menethil", (byte) 24);
        userDaoHibernate.saveUser("Vladimir", "Putin", (byte) 69);


        System.out.println(userDaoHibernate.getAllUsers());
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}

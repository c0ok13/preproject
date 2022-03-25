package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static final String DB_URL = "jdbc:mysql://localhost:3306/preproject";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args)
    {try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement()
    ) {
        String sql = """
                create table IF NOT EXISTS user
                (id       int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                 name     varchar(255) null,
                 lastName varchar(255) null,
                 age      tinyint      null
                 );""";

        stmt.executeUpdate(sql);
        System.out.println("Created table in given database...");

        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        user1.setName("Yaroslav");
        user1.setLastName("Mimeev");
        user1.setAge((byte) 22);
        String insertStatement =
                "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";
        PreparedStatement insertUser = conn.prepareStatement(insertStatement);
        insertUser.setString(1, user1.getName());
        insertUser.setString(2, user1.getLastName());
        insertUser.setByte(3, user1.getAge());
        insertUser.executeUpdate();
        System.out.println("Yaroslav added to databse");

        user2.setName("Ivan");
        user2.setLastName("Ivanov");
        user2.setAge((byte) 39);

        insertUser.setString(1, user2.getName());
        insertUser.setString(2, user2.getLastName());
        insertUser.setByte(3, user2.getAge());
        insertUser.executeUpdate();

        System.out.println("Ivan added to databse");

        user3.setName("Arthas");
        user3.setLastName("Menethil");
        user3.setAge((byte) 24);

        insertUser.setString(1, user3.getName());
        insertUser.setString(2, user3.getLastName());
        insertUser.setByte(3, user3.getAge());
        insertUser.executeUpdate();
        System.out.println("Arthas added to databse");

        user4.setName("Vladimir");
        user4.setLastName("Putin");
        user4.setAge((byte) 69);

        insertUser.setString(1, user4.getName());
        insertUser.setString(2, user4.getLastName());
        insertUser.setByte(3, user4.getAge());
        insertUser.executeUpdate();
        System.out.println("Vladimir added to databse");

        try (ResultSet rst = stmt.executeQuery("SELECT * FROM USER")) {
            while (rst.next()) {
                User user = new User();
                user.setId(rst.getLong(1));
                user.setName(rst.getString(2));
                user.setLastName(rst.getString(3));
                user.setAge(rst.getByte(4));
                System.out.println(user);
            }
        }
        stmt.execute("TRUNCATE TABLE USER");
        stmt.execute("DROP TABLE USER");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
}

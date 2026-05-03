package com.techtogether.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/techtogether_db",
                "root",
                "root"   // change if your password is different
            );
            System.out.println("Database connected successfully");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

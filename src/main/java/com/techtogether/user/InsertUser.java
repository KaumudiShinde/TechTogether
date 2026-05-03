package com.techtogether.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.techtogether.db.DBConnection;

public class InsertUser {

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();

            String sql =
              "INSERT INTO user_login (user_name, user_email, password, role) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "Rahul");
            ps.setString(2, "rahul@gmail.com");
            ps.setString(3, "12345");
            ps.setString(4, "CODER");

            ps.executeUpdate();
            System.out.println("User inserted");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

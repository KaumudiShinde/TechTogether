package com.techtogether.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.techtogether.db.DBConnection;

public class SelectUser {

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM user_login";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                    rs.getInt("userID") + " " +
                    rs.getString("user_name") + " " +
                    rs.getString("user_email") + " " +
                    rs.getString("role")
                );
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

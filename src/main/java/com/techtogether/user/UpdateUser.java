package com.techtogether.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.techtogether.db.DBConnection;

public class UpdateUser {

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();

            String sql =
              "UPDATE user_login SET role=? WHERE userID=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "ADMIN");
            ps.setInt(2, 1);

            ps.executeUpdate();
            System.out.println("User updated");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

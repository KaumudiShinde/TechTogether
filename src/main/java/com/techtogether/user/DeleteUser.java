package com.techtogether.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.techtogether.db.DBConnection;

public class DeleteUser {

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM user_login WHERE userID=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, 3);
            ps.executeUpdate();

            System.out.println("User deleted");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

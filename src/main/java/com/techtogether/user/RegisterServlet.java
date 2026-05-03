package com.techtogether.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.techtogether.db.DBConnection;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try {
            Connection con = DBConnection.getConnection();

            // ✅ STEP 1: Check if email already exists
            PreparedStatement checkStmt = con.prepareStatement(
                "SELECT * FROM user_login WHERE user_email = ?"
            );
            checkStmt.setString(1, email);

            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // ❌ Email already exists
                response.getWriter().println("Email already registered! Try another.");
                return; // stop execution
            }

            // ✅ STEP 2: Insert new user
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO user_login (user_name, user_email, password, role) VALUES (?, ?, ?, ?)"
            );

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);

            ps.executeUpdate();

            con.close();

            // ✅ Redirect only after successful insert
            response.sendRedirect(request.getContextPath() + "/login.html");

        } catch (Exception e) {
            e.printStackTrace();

            // Optional: show user-friendly message
            response.getWriter().println("Something went wrong. Try again.");
        }
    }
}
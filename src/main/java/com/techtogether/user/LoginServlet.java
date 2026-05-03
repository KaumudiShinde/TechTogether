package com.techtogether.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.techtogether.db.DBConnection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {

            // Get DB connection
            Connection con = DBConnection.getConnection();

            // Check user in database
            PreparedStatement ps = con.prepareStatement(
                "SELECT role, user_name FROM user_login WHERE user_email=? AND password=?"
            );

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                // Get role and name
                String role = rs.getString("role");
                String name = rs.getString("user_name");
                
                // Create session
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("name", name);
                session.setAttribute("role", role);

                // Redirect to dashboard
                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");

            }
            else{

                // Login failed → redirect back
                response.sendRedirect(request.getContextPath() + "/login.html");

            }

            con.close();

        } catch(Exception e){
            e.printStackTrace();

            // Error redirect
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
    }
}

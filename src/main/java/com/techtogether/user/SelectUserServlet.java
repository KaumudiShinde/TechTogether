package com.techtogether.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.techtogether.db.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/selectuser")
public class SelectUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>User List from Database</h2>");
        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Name</th><th>Email</th><th>Role</th></tr>");

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user_login");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("user_id") + "</td>");
                out.println("<td>" + rs.getString("user_name") + "</td>");
                out.println("<td>" + rs.getString("user_email") + "</td>");
                out.println("<td>" + rs.getString("user_role") + "</td>");
                out.println("</tr>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("</table>");
        out.println("<a href='login.html'>Back to Login</a>");
        out.println("</body></html>");
    }
}

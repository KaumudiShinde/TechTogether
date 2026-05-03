package com.techtogether.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.techtogether.db.DBConnection;

@WebServlet("/applyJob")
public class ApplyJobServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        String email = null;

        // ✅ OPTIONAL LOGIN CHECK (no redirect)
        if(session != null && session.getAttribute("email") != null){
            email = (String) session.getAttribute("email");
        } else {
            // If not logged in, you can allow or block
            response.getWriter().println("Please login to apply for jobs.");
            return;
        }

        try {
            int jobId = Integer.parseInt(request.getParameter("jobId"));

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO job_applications (user_email, job_id) VALUES (?, ?)"
            );

            ps.setString(1, email);
            ps.setInt(2, jobId);

            ps.executeUpdate();

            con.close();

            response.getWriter().println("Applied Successfully!");

        } catch(Exception e){
            e.printStackTrace();
            response.getWriter().println("Error while applying.");
        }
    }
}
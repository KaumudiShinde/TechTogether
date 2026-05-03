package com.techtogether.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.techtogether.db.DBConnection;

@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // Get file part
            Part filePart = request.getPart("resume");

            String fileName = filePart.getSubmittedFileName();

            // Save folder
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            // Save file
            filePart.write(uploadPath + File.separator + fileName);

            // Save file name in database
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO resumes(file_name) VALUES(?)"
            );

            ps.setString(1, fileName);

            ps.executeUpdate();

            con.close();

            // Redirect back to dashboard
            response.sendRedirect("dashboard.jsp");

        }
        catch(Exception e){

            e.printStackTrace();

            response.getWriter().println("Upload Failed: " + e.getMessage());
        }
    }
}

package com.techtogether.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.techtogether.db.DBConnection;

public class JobDAO {

    public List<Job> getAllJobs() {

        List<Job> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM jobs");

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Job j = new Job();

                j.setJobId(rs.getInt("job_id"));
                j.setTitle(rs.getString("title"));
                j.setDescription(rs.getString("description"));
                j.setJobType(rs.getString("job_type"));
                j.setDeadline(rs.getString("deadline"));

                list.add(j);
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}

package com.techtogether.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.techtogether.db.DBConnection;

public class InsertUserInfo {

public boolean insertUser(String name, String email, String password, String role){

boolean status = false;

try(Connection con = DBConnection.getConnection()) {

String sql = "INSERT INTO user_login(user_name, user_email, password, role, created_at) "
+ "VALUES(?,?,?,?, NOW())";

PreparedStatement ps = con.prepareStatement(sql);
ps.setString(1, name);
ps.setString(2, email);
ps.setString(3, password);
ps.setString(4, role);

int rows = ps.executeUpdate();

if(rows > 0){
status = true;
}

} catch(Exception e){
e.printStackTrace();
}

return status;
}
}

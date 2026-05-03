<%@ page session="true" %>
<%
String email = (String) session.getAttribute("email");
String role  = (String) session.getAttribute("role");

if(email == null){
   response.sendRedirect("login.html");
   return;
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Dashboard</title>
<link rel="stylesheet" href="css/dashboard.css">
</head>
<body>

<h2>Welcome, <%= email %></h2>
<h3>Your Role: <%= role %></h3>

<div class="card">
<h2>Coder Dashboard</h2>

<a class="btn" href="upload.jsp">Upload Resume</a>
<a class="btn" href="viewJobs.jsp">View Jobs</a>
<a class="btn" href="LogoutServlet">Logout</a>

</div>
</body>
</html>

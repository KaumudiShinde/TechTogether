<%@ page import="java.util.List" %>
<%@ page import="com.techtogether.user.JobDAO" %>
<%@ page import="com.techtogether.user.Job" %>

<%
String email = (String) session.getAttribute("email"); // optional (not forcing login)

// Get jobs list
JobDAO dao = new JobDAO();
List<Job> jobs = dao.getAllJobs();
%>

<!DOCTYPE html>
<html>
<head>
<title>Available Jobs | TechTogether</title>
<link rel="stylesheet" href="css/jobs.css">

<style>
body{
    font-family: Arial;
    background: linear-gradient(135deg,#4facfe,#00f2fe);
    padding: 30px;
}
.container{
    max-width: 800px;
    margin: auto;
}
.card{
    background: white;
    padding: 20px;
    margin-bottom: 15px;
    border-radius: 8px;
    box-shadow: 0 4px 10px rgba(0,0,0,0.2);
}
button{
    padding: 8px 15px;
    background: #4facfe;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
button:hover{
    background: #00c6ff;
}
.back{
    display: inline-block;
    margin-bottom: 20px;
    padding: 8px 15px;
    background: white;
    text-decoration: none;
    border-radius: 5px;
}
</style>
</head>

<body>

<div class="container">

<h2 style="color:white;">Available Jobs</h2>

<a href="dashboard.jsp" class="back">Back</a>

<%
if(jobs == null || jobs.size() == 0){
%>
    <h3 style="color:white;">No jobs available</h3>
<%
} else {
    for(Job j : jobs){
%>

<div class="card">

<h3><%= j.getTitle() %></h3>

<p><b>Description:</b> <%= j.getDescription() %></p>

<p><b>Job Type:</b> <%= j.getJobType() %></p>

<p><b>Deadline:</b> <%= j.getDeadline() %></p>

<form action="applyJob" method="post">
    <input type="hidden" name="jobId" value="<%= j.getJobId() %>">
    <button type="submit">Apply Now</button>
</form>

</div>

<%
    }
}
%>

</div>

</body>
</html>
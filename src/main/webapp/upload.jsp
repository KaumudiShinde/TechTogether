<!DOCTYPE html>
<html>
<head>
<title>Upload Resume</title>
<link rel="stylesheet" href="css/pages.css">
</head>
<body>

<h2>Upload Resume</h2>

<form action="UploadServlet" method="post" enctype="multipart/form-data">

<input type="file" name="resume" required>

<button type="submit">Upload</button>

</form>


<a href="dashboard.jsp">Back</a>

</body>
</html>

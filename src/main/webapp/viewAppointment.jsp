<!DOCTYPE html>
<html>
<head>
<title>View Appointment</title>
</head>
<body>
<h2>View Appointment</h2>
<form action="MainServlet" method="post">
<input type="hidden" name="operation" value="viewRecord">
Patient Name:<input type="text" name="patientName"><br><br>
Appointment Date:<input type="date" name="appointmentDate"><br><br>
<input type="submit" value="Search">
</form>
</body>
</html>

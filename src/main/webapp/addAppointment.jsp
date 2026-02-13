<!DOCTYPE html>
<html>
<head>
<title>Add Appointment</title>
</head>
<body>
<h2>Add Appointment</h2>
<form action="MainServlet" method="post">
<input type="hidden" name="operation" value="newRecord">
Patient Name:<input type="text" name="patientName"><br><br>
Doctor Name:<input type="text" name="doctorName"><br><br>
Appointment Date:<input type="date" name="appointmentDate"><br><br>
Consultation Type:
<select name="consultationType">
<option value="General">General</option>
<option value="Specialist">Specialist</option>
</select><br><br>
Fees:<input type="number" name="fees"><br><br>
Remarks:<input type="text" name="remarks"><br><br>
<input type="submit" value="Add Record">
</form>
</body>
</html>

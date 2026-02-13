<%@ page import="com.wipro.clinic.bean.ClinicBean" %>
<!DOCTYPE html>
<html>
<body>
<h2>Appointment Details</h2>
<%
ClinicBean b=(ClinicBean)request.getAttribute("bean");
String msg=(String)request.getAttribute("msg");
if(b!=null){
%>
Record ID:<%=b.getRecordId()%><br>
Patient Name:<%=b.getPatientName()%><br>
Doctor Name:<%=b.getDoctorName()%><br>
Date:<%=b.getAppointmentDate()%><br>
Type:<%=b.getConsultationType()%><br>
Fees:<%=b.getFees()%><br>
Remarks:<%=b.getRemarks()%><br>
<%
}else if(msg!=null){
    out.println(msg);
}
%>
<br><a href="menu.html">Back</a>
</body>
</html>
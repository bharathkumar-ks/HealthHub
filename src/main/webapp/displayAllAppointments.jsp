<%@ page import="java.util.*,com.wipro.clinic.bean.ClinicBean" %>
<!DOCTYPE html>
<html>
<body>
<h2>All Appointment Records</h2>
<%
List<ClinicBean> list = (List<ClinicBean>)request.getAttribute("list");
String msg = (String)request.getAttribute("msg");
if(list != null && !list.isEmpty()){
    for(ClinicBean b : list){
%>
<hr>
Record ID: <%= b.getRecordId() %><br>
Patient: <%= b.getPatientName() %><br>
Doctor: <%= b.getDoctorName() %><br>
Date: <%= b.getAppointmentDate() %><br>
Type: <%= b.getConsultationType() %><br>
Fees: <%= b.getFees() %><br>
Remarks: <%= b.getRemarks() %><br>

<%
    }
} else if(msg != null){
    out.println(msg);
}
%>

<br><a href="menu.html">Back</a>

</body>
</html>

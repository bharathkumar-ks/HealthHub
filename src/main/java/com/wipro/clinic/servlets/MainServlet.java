package com.wipro.clinic.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.wipro.clinic.bean.ClinicBean;
import com.wipro.clinic.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet{
    private Administrator admin=new Administrator();
    public String addRecord(HttpServletRequest request){
        try{
            ClinicBean bean = new ClinicBean();
            bean.setPatientName(request.getParameter("patientName"));
            bean.setDoctorName(request.getParameter("doctorName"));
            bean.setConsultationType(request.getParameter("consultationType"));
            bean.setRemarks(request.getParameter("remarks"));
            String feesStr=request.getParameter("fees");
            if(feesStr!=null && !feesStr.isEmpty()){
                bean.setFees(Integer.parseInt(feesStr));
            }
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date d=sdf.parse(request.getParameter("appointmentDate"));
            bean.setAppointmentDate(d);
            return admin.addRecord(bean);
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }
    }

    public ClinicBean viewRecord(HttpServletRequest request) {
        try{
            String name=request.getParameter("patientName");
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date d=sdf.parse(request.getParameter("appointmentDate"));
            return admin.viewRecord(name, d);
        }catch (Exception e){
            return null;
        }
    }

    public List<ClinicBean>viewAllRecords(HttpServletRequest request) {
        return admin.viewAllRecords();
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        String op=request.getParameter("operation");
        if("newRecord".equals(op)){
            String result=addRecord(request);
            if (result==null||result.equals("FAIL")||result.startsWith("INVALID")||result.equals("ALREADY EXISTS")){
                response.sendRedirect("error.html");
            } else {
                response.sendRedirect("success.html");
            }
        }
        else if("viewRecord".equals(op)){
            ClinicBean bean=viewRecord(request);
            if (bean == null){
                request.setAttribute("msg", "No matching records exists! Please try again!");
            }
            else{
                request.setAttribute("bean", bean);
            }
            request.getRequestDispatcher("displayAppointment.jsp").forward(request, response);
        }
        else if ("viewAllRecords".equals(op)){
            List<ClinicBean>list=viewAllRecords(request);
            if (list.isEmpty()){
                request.setAttribute("msg", "No records available!");
            }else{
                request.setAttribute("list", list);
            }
            request.getRequestDispatcher("displayAllAppointments.jsp").forward(request, response);
        }
    }
}
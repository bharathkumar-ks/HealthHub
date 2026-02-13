package com.wipro.clinic.dao;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.wipro.clinic.bean.ClinicBean;
import com.wipro.clinic.util.DBUtil;

public class ClinicDAO {
    public String createRecord(ClinicBean bean) {
        String sql="INSERT INTO CLINIC_TB VALUES (?,?,?,?,?,?,?)";
        try (Connection con=DBUtil.getDBConnection();
             PreparedStatement ps=con.prepareStatement(sql)){
            ps.setString(1,bean.getRecordId());
            ps.setString(2,bean.getPatientName());
            ps.setString(3,bean.getDoctorName());
            ps.setDate(4,new java.sql.Date(bean.getAppointmentDate().getTime()));
            ps.setString(5,bean.getConsultationType());
            ps.setInt(6,bean.getFees());
            ps.setString(7,bean.getRemarks());
            int rows=ps.executeUpdate();
            if (rows>0){
            	return bean.getRecordId();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return "FAIL";
    }

    public ClinicBean fetchRecord(String patientName, java.util.Date appointmentDate) {
        String sql="SELECT * FROM CLINIC_TB WHERE PATIENTNAME=? AND APPOINTMENT_DATE=?";
        ClinicBean bean=null;
        try (Connection con=DBUtil.getDBConnection();
             PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setString(1,patientName);
            ps.setDate(2,new java.sql.Date(appointmentDate.getTime()));
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                bean=mapRow(rs);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }

    public boolean recordExists(String patientName,java.util.Date appointmentDate){
        return fetchRecord(patientName, appointmentDate)!=null;
    }

    public List<ClinicBean> fetchAllRecords(){
        List<ClinicBean> list=new ArrayList<>();
        String sql="SELECT * FROM CLINIC_TB";
        try (Connection con=DBUtil.getDBConnection();
             PreparedStatement ps=con.prepareStatement(sql);
             ResultSet rs=ps.executeQuery()){
            while (rs.next()){
                list.add(mapRow(rs));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public String generateRecordID(String patientName, java.util.Date appointmentDate){
        String id="";
        try (Connection con=DBUtil.getDBConnection();
             PreparedStatement ps=con.prepareStatement("SELECT CLINIC_SEQ.NEXTVAL FROM DUAL");
             ResultSet rs=ps.executeQuery()){
            int seq=0;
            if (rs.next())seq=rs.getInt(1);
            DateFormat df=new SimpleDateFormat("yyyyMMdd");
            String datePart=df.format(appointmentDate);
            String namePart=patientName.substring(0, 2).toUpperCase();
            String seqPart=String.format("%02d", seq);
            id=datePart+namePart+seqPart;
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }

    private ClinicBean mapRow(ResultSet rs) throws Exception{
        ClinicBean b = new ClinicBean();
        b.setRecordId(rs.getString("RECORDID"));
        b.setPatientName(rs.getString("PATIENTNAME"));
        b.setDoctorName(rs.getString("DOCTORNAME"));
        b.setAppointmentDate(rs.getDate("APPOINTMENT_DATE"));
        b.setConsultationType(rs.getString("CONSULTATION_TYPE"));
        b.setFees(rs.getInt("FEES"));
        b.setRemarks(rs.getString("REMARKS"));
        return b;
    }
}
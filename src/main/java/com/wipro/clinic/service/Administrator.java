package com.wipro.clinic.service;
import java.util.Date;
import java.util.List;
import com.wipro.clinic.bean.ClinicBean;
import com.wipro.clinic.dao.ClinicDAO;
import com.wipro.clinic.util.InvalidInputException;

public class Administrator {
    private ClinicDAO dao=new ClinicDAO();
    public String addRecord(ClinicBean bean) {
        try{
            if(bean==null||bean.getPatientName()==null||bean.getAppointmentDate()==null) {
                throw new InvalidInputException();
            }
            if(bean.getPatientName().length()<2){
                return "INVALID PATIENT NAME";
            }
            Date today=new Date();
            if(bean.getAppointmentDate().before(today)){
                return "INVALID DATE";
            }

            if(dao.recordExists(bean.getPatientName(),bean.getAppointmentDate())) {
                return "ALREADY EXISTS";
            }
            String id=dao.generateRecordID(bean.getPatientName(),bean.getAppointmentDate());
            bean.setRecordId(id);
            return dao.createRecord(bean);
        }catch (InvalidInputException e){
            return "INVALID INPUT";
        }
    }

    public ClinicBean viewRecord(String patientName,Date appointmentDate) {
        return dao.fetchRecord(patientName,appointmentDate);
    }
    
    public List<ClinicBean>viewAllRecords() {
        return dao.fetchAllRecords();
    }
}
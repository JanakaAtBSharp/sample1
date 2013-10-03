package com.bsharp.sample1.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bsharp.sample1.model.Patient;

import com.bsharp.sample1.service.PatientService;

import com.bsharp.sample1.service.impl.PatientServiceImpl;



public class PatientManager {

	private final Logger log = Logger.getLogger(PatientServiceImpl.class);
	
	public void savePatient(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		
		log.debug("PatientManager savePatient method called...");
		Map<String, String> validationMap=new HashMap<String, String>();
		Map<String, String> resultsMap;
		
		String action=req.getParameter("action");
		
		String healthRec =req.getParameter("healthrec");
		validationMap.put("healthrec", healthRec);
		
		String age =req.getParameter("age");
		validationMap.put("age", age);
				
		//Validating fields
		resultsMap=validateFields(validationMap);
		req.setAttribute("validateResults", resultsMap);
		
		if(resultsMap.size()<1){
			log.info("Fields validation success...");
			//Creating Patient POJO
			Patient patient=new Patient();
			patient.setHealthRecNo(Integer.parseInt(healthRec));
			patient.setAge(Integer.parseInt(age));
			patient.setFirstName(req.getParameter("fname"));
			patient.setLastName(req.getParameter("lname"));
			patient.setGender(req.getParameter("gender"));
			
			PatientService patientService=new PatientServiceImpl();			
			try {
				patientService.savePatient(patient,action);
				req.setAttribute("result", "Saved Successfully...");
				log.info("Patient saved successfully...");
			} catch (SQLException e) {				
				req.setAttribute("result", "Error :"+e.toString());
				log.error(e.toString());
			}
		}else{
			log.error("Fields validation failed...");
		}
		
		// Dispatch request object to AddPatient.jsp		
		RequestDispatcher view=req.getRequestDispatcher("AddPatient.jsp");
		view.forward(req, res);
	}
	
	// Validate given map of fields and return relevant message
	private Map<String, String> validateFields(Map<String, String> validationMap){
		
		Map<String, String> resultsMap=new HashMap<String, String>();
		int intValue=0;
		
		for( Map.Entry<String, String> entry:validationMap.entrySet()){
			if(entry.getKey().equals("healthrec")){
				try{
					intValue=Integer.parseInt(entry.getValue());
				} catch(Exception e){
					if(entry.getValue()!=""){
						resultsMap.put("healthrec", "*Health Record should be a number.");
					}else{
						resultsMap.put("healthrec","*Health Record must be filled.");
					}
				}	
			}else if(entry.getKey().equals("age")){
				try{
					intValue=Integer.parseInt(entry.getValue());
				} catch(Exception e){
					if(entry.getValue()!=""){
						resultsMap.put("age", "*Age should be a number.");
					}else{
						resultsMap.put("age","*Age must be filled.");
					}
				}
				if(intValue<0){
					resultsMap.put("age","*Age should not be negative.");
				}
			}
		}
		return resultsMap;	
	}
	
	public void ListPatient(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{				
		log.debug("PatientManager ListPatient method called...");
		PatientService patientService=new PatientServiceImpl();
	    List<Patient> patientList=patientService.listPatient();
	    
	    request.setAttribute("list",patientList );	    
	    // Dispatch request object to ListPatient.jsp
	    RequestDispatcher view=request.getRequestDispatcher("ListPatient.jsp");
		view.forward(request, response);
	}
	
	public void editPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{			
		log.debug("PatientManager editPatient method called...");
		int healthRecord=Integer.parseInt(request.getParameter("healthRecord"));
		PatientService patientService=new PatientServiceImpl();
		  
		Patient patient=patientService.getPatient(healthRecord);
		request.setAttribute("patient", patient);
		RequestDispatcher view=request.getRequestDispatcher("AddPatient.jsp");
		view.forward(request, response);		
	}
	
	public void deletePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		log.debug("PatientManager deletePatient method called...");
		int healthRecord=Integer.parseInt(request.getParameter("healthrec"));

		PatientService patientService=new PatientServiceImpl();
		try{
			if(patientService.deletePatient(healthRecord)>=1){
				request.setAttribute("result", "Deleted Successfully...");
				log.info("Patient deleted successfully...");
			}
		} catch (SQLException e) {			
			request.setAttribute("result", "Error :"+e.toString());
			log.error(e.toString());
		}
		RequestDispatcher view=request.getRequestDispatcher("AddPatient.jsp");
		view.forward(request, response);
	}

}

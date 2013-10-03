
package com.bsharp.sample1.web;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.bsharp.sample1.service.impl.PatientServiceImpl;




public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(PatientServiceImpl.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		String action = request.getParameter("action");
		log.info("*** Action is "+action);
		
		if(action.equals("Save") || action.equals("Update")){
			PatientManager patientManager=new PatientManager();
			patientManager.savePatient(request,response);
		}
		else if(action.equals("View patients>>")){
			PatientManager patientManager=new PatientManager();
			patientManager.ListPatient(request, response);
		}
	    else if(action.equals("<<Create patients")){
			RequestDispatcher view=request.getRequestDispatcher("AddPatient.jsp");
			view.forward(request, response);
		}
	    else if(action.equals("Get")){
	        PatientManager patientManager=new PatientManager();
	        patientManager.editPatient(request, response);	    	
	    }
	    else if(action.equals("Delete")){
	    	PatientManager patientManager=new PatientManager();
	    	patientManager.deletePatient(request, response);
	    }
	}

}

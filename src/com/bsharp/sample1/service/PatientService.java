package com.bsharp.sample1.service;

import java.sql.SQLException;
import java.util.List;

import com.bsharp.sample1.model.Patient;


public interface PatientService {

	public void savePatient(Patient patient,String action) throws SQLException;
	public List<Patient> listPatient();
	public Patient getPatient(int healthRecord);
	public int deletePatient(int healthRecord)throws SQLException;
}

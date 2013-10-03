package com.bsharp.sample1.dao;

import java.sql.SQLException;
import java.util.List;

import com.bsharp.sample1.model.Patient;

public interface PatientDAO {

	public void addPatient(Patient patient)throws SQLException;
	public void updatePatient(Patient patient)throws SQLException;
	public List<Patient> getlistPatient() throws ClassNotFoundException, SQLException;
	public Patient getPatient(int healthRecord);
	public int deletePatient(int healthRecord)throws SQLException;

}

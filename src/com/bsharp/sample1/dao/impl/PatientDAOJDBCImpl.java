package com.bsharp.sample1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import com.bsharp.sample1.dao.PatientDAO;
import com.bsharp.sample1.model.Patient;

public class PatientDAOJDBCImpl implements PatientDAO {

	private final Logger log = Logger.getLogger(PatientDAOJDBCImpl.class);
	
	@Override
	public void updatePatient(Patient patient) throws SQLException {
		// Get JDBC Connection
		Connection conn= JDBCManager.getConnection();
				
		PreparedStatement stmt=null;
		String sql="Update Patient "+
						   "SET FirstName=?,LastName=?,Gender=?,Age=? "+
						   "WHERE HealthRecordNumber=?";
						
		try {
			stmt=conn.prepareStatement(sql);
			//Extract patient informations and set to Prepared Statement
					
			stmt.setString(1, patient.getFirstName());
			stmt.setString(2, patient.getLastName());
			stmt.setString(3, patient.getGender());
			stmt.setInt(4, patient.getAge());
			stmt.setInt(5, patient.getHealthRecNo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			// Closing the connections
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		}		
	}
	
	@Override
	public void addPatient(Patient patient)throws SQLException {
		
		// Get JDBC Connection
		Connection conn= JDBCManager.getConnection();
		
		PreparedStatement stmt=null;
		String sql="INSERT INTO Patient "+
				   "(HealthRecordNumber,FirstName,LastName,Gender,Age) VALUES "+
				   "(?,?,?,?,?)";
				
		try {
			stmt=conn.prepareStatement(sql);
			//Extract patient informations and set to Prepared Statement
			stmt.setInt(1, patient.getHealthRecNo());
			stmt.setString(2, patient.getFirstName());
			stmt.setString(3, patient.getLastName());
			stmt.setString(4, patient.getGender());
			stmt.setInt(5, patient.getAge());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			// Closing the connections
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		}		
	}

	@Override
	public List<Patient> getlistPatient() {
		
		BasicConfigurator.configure();
		log.debug("getlistPatient method called...");
		
	    List<Patient> list=new ArrayList<Patient>();
	   
		Statement stmt = null;
		try {
			stmt = JDBCManager.getConnection().createStatement();
			String sql = "SELECT * FROM Patient";
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				 Patient pt=new Patient();
				 pt.setHealthRecNo(rs.getInt("HealthRecordNumber"));
				 pt.setFirstName(rs.getString("FirstName"));
				 pt.setLastName(rs.getString("LastName"));
				 pt.setGender(rs.getString("Gender"));
				 pt.setAge(rs.getInt("Age"));
				 list.add(pt);
            }
			
		 } catch (SQLException e) {
             e.printStackTrace();
		 }
		 finally{
             try {
		         JDBCManager.getConnection().close();
			 } catch (SQLException e) {
		         e.printStackTrace();
			}
		}
		return list;
       }

	@Override
	public Patient getPatient(int healthRecord) {
		log.debug("getPatient method called...");
		PreparedStatement stmt = null;
		Patient pt=null;
		try {
			
			String sql = "SELECT * FROM Patient where HealthRecordNumber=?";
			stmt = JDBCManager.getConnection().prepareStatement(sql);
			stmt.setInt(1,healthRecord);
			ResultSet rs = stmt.executeQuery();
						
			while(rs.next()){
				  pt=new Patient();
				 pt.setHealthRecNo(rs.getInt("HealthRecordNumber"));
				 pt.setFirstName(rs.getString("FirstName"));
				 pt.setLastName(rs.getString("LastName"));
				 pt.setGender(rs.getString("Gender"));
				 pt.setAge(rs.getInt("Age"));				 
            }			
		 } catch (SQLException e) {
             log.error(e.toString());
		 }finally{
             try {
		          JDBCManager.getConnection().close();
			 } catch (SQLException e) {
				 log.error(e.toString());
			 }
		 }
		return pt;
	}

	@Override
	public int deletePatient(int healthRecord)throws SQLException {	
		log.debug("deletePatient method called...");
		PreparedStatement stmt = null;
		int count=0;
		try{
			String sql="DELETE FROM Patient WHERE HealthRecordNumber=?";
			stmt = JDBCManager.getConnection().prepareStatement(sql);
			stmt.setInt(1,healthRecord);
			count = stmt.executeUpdate();		
        }catch(SQLException e){
        	throw e;			
		}
		return count;
	}			
}

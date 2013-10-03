<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*" import="com.bsharp.sample1.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style>
		#wrapper {
    	width: 600px;
    	margin: 0 auto;
    	text-align: left;
		}
	</style>

	<title>Add patient</title>
</head>

<body id="wrapper">
	<%
		Map<String,String> validateFields=(HashMap<String,String>)request.getAttribute("validateResults");
		Patient patient=(Patient)request.getAttribute("patient");
	%>
	<div>
		<form method="POST" action="Control.do">
			<br/><br/>
			<div style="background-color:#194775;text-align:center; height:40px">
				<br/>
  				<font face="verdana" color="white" size="4" >
  					<%if(patient!=null){
  					  	out.print("Edit Patient");	
  					  }else{
  						out.print("Create Patient");
  					  }
  					%>
  					
  				</font>
			</div>
			<div style="text-align:center">
				<font  color="red" size="3" >
  					<%  String result=(String)request.getAttribute("result");
  						if(result!=null){  
  							out.print(result);
  						}
  					  %>
  				</font>
  			</div>
			<table style="font:12px verdana,sans-serif;">
				<tr>
					<td>Health Record Number</td>
					<td><input type="text" name="healthrec"
					<%if(patient!=null){
						out.print("value="+patient.getHealthRecNo()+" readonly=\"readonly\" style=\"color: #787878;\"");
						out.print("<input type=\"hidden\" name=\"healthrec\"" +" value="+patient.getHealthRecNo());
					}
					%>	
					></td>
					<td>
					<font  color="red" size="2" >
						<%if(validateFields!=null && validateFields.containsKey("healthrec")){
							out.print(validateFields.get("healthrec"));
						  }
						%>
					</font>	
					</td>
				</tr>

				<tr>
					<td>First Name</td>
					<td><input type="text" name="fname" 
					<%if(patient!=null){
						out.print("value="+patient.getFirstName());
					}
					%>					
					></td>
				</tr>

				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lname"
					<%if(patient!=null){
						out.print("value="+patient.getLastName());
					}
					%>	
					></td>
				</tr>

				<tr>
					<td>Gender</td>
					<td>
						<select name="gender" size="1">
							<option <%if(patient!=null && patient.getGender().equals("Male")){out.print("selected");}%>>Male
							<option <%if(patient!=null && patient.getGender().equals("Female")){out.print("selected");}%>>Female
							<option <%if(patient!=null && patient.getGender().equals("Unknown")){out.print("selected");}%>>Unknown
						</select>
					</td>
				</tr>


				<tr>
					<td>Age</td>
					<td><input type="text" name="age"
					<%if(patient!=null){
						out.print("value="+patient.getAge());
					}
					%>	
					></td>
					<td>
					<font  color="red" size="2" >
						<%if(validateFields!=null && validateFields.containsKey("age")){
							out.print(validateFields.get("age"));
						  }
						%>
					</font>	
					</td>
				</tr>

				<br>
				<tr>
					<td></td>
					<td><input type="submit" name="action" 
					<%if(patient!=null){
						out.print(" value=Update");
					}else{
						out.print(" value=Save");
					}
					%>
					>
					
					     &nbsp;&nbsp;<input type="submit" name="action" value="Delete"
					     <%if(patient==null){%>
					      disabled="disabled"><%} %>
					</td>
					
				</tr>

			</table>
			<br><br>
			<div style="background-color:#194775;text-align:center;height:6px">

			</div>
			<div style="align:center"><input type="submit" name="action" value="View patients>>"></div>
			<div style="background-color:#194775;text-align:center;height:6px">

			</div>
		</form>
	</div>
</body>
</html>
<%@page import="com.bsharp.sample1.dao.impl.PatientDAOJDBCImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"
    import="com.bsharp.sample1.model.Patient"
   %>
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

       table, td{
                border: 1px solid black;
				}
        th      {
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #666666;
			background-color: #dedede;
		      }
   </style>
  <title>List Patient</title>
      </head>
<body id="wrapper">
	<%
	List<Patient> list=new ArrayList<Patient>();
	list=(List<Patient>)request.getAttribute("list");
	String[][] s=new String[list.size()][5];
	
	%>
<div>
   <!--  <form method="post" action="/DemoMVC/Control/<<Create patients">-->
   <form method="post" action="Control.do">
      <br/><br/>
         <div style="background-color:#194775;text-align:left;text-align:center; height:40px ">
            <br/>
            <font face="verdana" color="white" size="4">List Of Patients</font>
         </div>
            <br/>
  <% if(list.size()>0){%>
               <table style="font:12px verdana,sans-serif; border:2px">
                  <tr>
						<th>HealthRecord#</th>
						<th>First Name</th>
						<th>LastName</th>
						<th>Gender</th>
						<th>Age</th>
				 </tr>
				<tr>
					   <% 
					    for(int i=0;i<=list.size()-1;i++){
						s[i][0]= Integer.toString(list.get(i).getHealthRecNo());
						s[i][1]=list.get(i).getFirstName();
						s[i][2]=list.get(i).getLastName();
						s[i][3]=list.get(i).getGender();
						s[i][4]=Integer.toString(list.get(i).getAge());%>


						<td><a href="Control.do?action=Get&healthRecord=<%=s[i][0] %>"><%=s[i][0] %></a></td>

						<td><%=s[i][1] %></td>
						<td><%=s[i][2] %></td>
						<td><%=s[i][3] %></td>
						<td><%=s[i][4] %></td>
				</tr>
						<%} %>
              </table>
				<%}else{%>
						<h4 style="color:red">No item to display</h4>
				<% }%>
							<br/>
<input type="hidden" value="ListPatient" id="patientHidden" name="patientHidden">
    <div style="background-color:#194775;text-align:center;height:6px"></div>
     	<div style="align:center"><input type="submit" value="<<Create patients" name="action"></div>
        	<div style="background-color:#194775;text-align:center;height:6px"></div>
           
	</form>
</div>

</body>
</html>
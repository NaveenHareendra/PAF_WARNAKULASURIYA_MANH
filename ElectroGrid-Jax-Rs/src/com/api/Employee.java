package com.api;
//it20032524 Warnakulasuriya M.A.N.H
import javax.ws.rs.Path;

import com.models.client;
import com.models.complaint;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import com.models.employee;
import com.dbService.databaseConnectionService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.PUT;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import com.dbService.*;
@Path("/Employee")

public class Employee {
	complaint updateComplaint = new complaint();
	employee regEmployee = new employee();
	employee logEmployee = new employee();
	
	
	databaseConnectionService dbConnect = new databaseConnectionService();
	complaintService complaintDbService = new complaintService();
	
	@Path("/Register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response Register(@FormParam("empNic") String empNic,
			@FormParam("empName") String empName,
			@FormParam("empAddress") String empAddress,
			@FormParam("empEmail") String empEmail,
			@FormParam("empPassword") String empPassword){//Employee Register function
		
		if(empNic.length()!=0 && empAddress.length()!=0 && empName.length()!=0 && empEmail.length()!=0  && empPassword.length()!=0 ){
		
			regEmployee.setEmpNic(empNic);
			regEmployee.setEmpEmail(empEmail);
			regEmployee.setEmpAddress(empAddress);
			regEmployee.setEmpName(empName);
			regEmployee.setEmpPassword(empPassword);

//		System.out.println("Register()");						
			boolean checkInsert=dbConnect.registerEmployeeDatabase(
				regEmployee.getEmpNic(),
				regEmployee.getEmpAddress(),
				regEmployee.getEmpEmail(),
				regEmployee.getEmpName(),
				regEmployee.getEmpPassword());
			
			if(checkInsert == true){
				String output = "{\"status\":\"success\",\"data\": \"Register Success\" }";
				Response response = Response.status(200).entity(output)
						.type(MediaType.APPLICATION_JSON)
						.build();
				return response;
			}else{
				String output = "{\"status\":\"success\",\"data\": \"Register Failed\" }";
				Response response = Response.status(200).entity(output)
						.type(MediaType.APPLICATION_JSON)
						.build();
				return response;
			}
		}else{
			System.out.println("Entered empty values...");
		}
		String output = "{\"status\":\"success\",\"data\": \"Register Failed\" }";
		Response response = Response.status(200).entity(output)
				.type(MediaType.APPLICATION_JSON)
				.build();
		return response;
		
	}
	
	@GET
	@Path("/Login/{employeeEmail}/{employeePassword}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response employeeLogin(@PathParam("employeeEmail") String empEmail, 
			@PathParam("employeePassword") String empPassword){//Employee Login Function
		System.out.println("Email:"+empEmail);
		System.out.println("password:"+empPassword);
		logEmployee.setEmpEmail(empEmail);
		logEmployee.setEmpPassword(empPassword);
		//ArrayList is used to store the values of the user profile.
		ArrayList<String> profileInformation = new ArrayList<String>();
		
		if((empEmail.length()!=0) && (empPassword.length()!=0)){

			System.out.println("called!");	
			
			try{//Add to the ArrayList
				profileInformation.addAll(dbConnect.employeeLogin(logEmployee.getEmpEmail(), logEmployee.getEmpPassword()));
				System.out.println("User Logged Successfully!");	
				System.out.println("Employee Name:"+profileInformation.get(0)+"\nEmployee Address: "+profileInformation.get(1)+"\nEmployee Email: "+profileInformation.get(2));
				

			}catch(NullPointerException ArrNull){
				System.out.println("Invalid Login Credentials, Please try again...");
			}
		}else {
		
			System.out.println("Empty data cannot be inserted...");
		
		}
		employee empDetails = new employee();
		empDetails.setEmpEmail(profileInformation.get(1));
		empDetails.setEmpPassword(profileInformation.get(2));
//		return "Employee Name: "+profileInformation.get(0)+"\nEmployee Email: "+profileInformation.get(1)+"\nEmployee Address: "+profileInformation.get(2);
//	
		System.out.println(empDetails.getEmpEmail());
		
		String output = "{\"status\":\"success\",\"data\": {\"email\":\""+empDetails.getEmpEmail()+"\"} }"; 
		Response response = Response.status(200).entity(output)
				.type(MediaType.APPLICATION_JSON)
				.build();
		System.out.println(output);
		return response;
	}
	
	

	//Complaint Status update of the customer, by Employee...
	@Path("/customerComplaintUpdate")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response customerComplaintUpdate(	
			@FormParam("complaintStatus") boolean cmplStat,
			@FormParam("ComplaintNo") int cmplNo,
			@FormParam("customerId") int cusId){
		
		System.out.println("Update in Progress...");
		
		updateComplaint.setComplaintStatus(cmplStat);
		updateComplaint.setComplaintNo(cmplNo);
		boolean complaintUpdated=complaintDbService.updateComplaint(cusId,updateComplaint.getComplaintStatus(), updateComplaint.getComplaintNo());
	
		if(complaintUpdated==true){
			String output = "{\"status\":\"success\",\"data\": \"Update Success\" }";
			Response response = Response.status(200).entity(output)
					.type(MediaType.APPLICATION_JSON)
					.build();
			System.out.println("Status updated...");
			return response;
		}else{
			String output = "{\"status\":\"success\",\"data\": \"Update Failed\" }";
			Response response = Response.status(200).entity(output)
					.type(MediaType.APPLICATION_JSON)
					.build();
			System.out.println("Something is wrong...");
			return response;
		}
		

	}
	

	@Path("/DeleteCustomerComplaint")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.APPLICATION_JSON) 
	@DELETE
	public Response complaintDelete(@FormParam("complaintId") int complaintId, 
			@FormParam("CustomerId") int  CustomerId){//Complaint Delete Function
		
		System.out.println("This is called ..."+CustomerId);

		boolean isDeleted=complaintDbService.deleteComplaint(complaintId, CustomerId);
		
		if(isDeleted==true){
			String output = "{\"status\":\"success\",\"data\": \"Delete Success\" }";
			Response response = Response.status(200).entity(output)
					.type(MediaType.APPLICATION_JSON)
					.build();
		    return response;
		}else{
			String output = "{\"status\":\"success\",\"data\": \"Delete Fail\"} }";
			Response response = Response.status(400).entity(output)
					.type(MediaType.APPLICATION_JSON)
					.build();
		    return response;
		}
		
	}
	
	@GET
	@Path("/readComplaints")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readComplaints(){// Read Complaints function for employee
		String output2=complaintDbService.readComplaints();
//		String output = "{\"status\":\"success\",\"data\": [{\"id\":\"1\",\"name\":\"John Doe\"},{\"id\":\"1\",\"name\":\"John Doe\"}]}"; 
//		JSONObject inputJSONOBject = new JSONObject(output);
		System.out.println("Output:"+output2);
		Response response = Response.status(200).entity(output2)
				.type(MediaType.APPLICATION_JSON)
				.build();

		return response;
	}
		
		
	
}
	
	


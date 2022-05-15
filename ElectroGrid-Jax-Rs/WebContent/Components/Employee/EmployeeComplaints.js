$(document).ready(function(){
	
	$.ajax({
		type:'get',
		dataType:'JSON',
		url:'Api/Employee/readComplaints',
		success:function(result){
//			alert("function was a success");
			 $("#Length").append(result.data.length);
			 let lengthOfData = result.data.length;
			 
			 for(let count=0; count<lengthOfData; count++){
				 var card=getComplaintCard(result.data[count].CustomerId, result.data[count].Complaint_No, result.data[count].Description,result.data[count].Complaint_Status);
				 $("#cardRows").append(card);
			 }
		},
		error:function(e){
			alert("Error:"+e);
		}
	});
	
	//alert('Function was called on page load');

});


function getComplaintCard(customerId, ComplaintId, Description, ComplaintStatus) 
{ 
     var complaint = ""; 

     var status = (ComplaintStatus=="true")?"Solved":"not solved";
     
     complaint += "<div class=\"complaint card bg-light m-2\" style=\"max-width: 30rem; float: left;\">"; 

     complaint += "<div class=\"card-body\">"; 
 
     complaint +="Customer ID:"+ customerId + "<br/>Complaint ID:<text class=\"complaint\" id="+ComplaintId+">"+ ComplaintId+"</text>"; 
 
     complaint += "<br/>"; 
 
     complaint += "Description:"+Description; 
     
     complaint += "<br/>"; 
     
     complaint += "Status:"+status; 
 
     complaint += "</div>"; 
     
     complaint +="<div class=\"btn-group-vertical\" role=\"group\" aria-label=\"Basic example\">";
     complaint += "<input type=\"radio\" onClick=\"updateCompletion("+customerId+","+ComplaintId+","+true+")\" value=\"Update\" id=\"flexCheckChecked\" name=\"flexRadioDefault\" class=\"form-check-input\" checked>"; 
     complaint +="<label class=\"form-check-label\" for=\"flexRadioDefault2\">Completed</label>";
     complaint += "<input type=\"radio\" onClick=\"updateCompletion("+customerId+","+ComplaintId+","+false+")\" value=\"Update\" id=\"flexCheckChecked\" name=\"flexRadioDefault\"  class=\"form-check-input\">"; 
     complaint +="<label class=\"form-check-label\" for=\"flexRadioDefault2\">Still Not Completed</label>";
     complaint += "</div>";
     complaint += "<br/>"; 
     complaint += "<input onClick=\"remove("+ComplaintId+","+ customerId+")\" type=\"button\" value=\"Remove\" id="+ComplaintId+" class=\"btn btn-danger  remove\">"; 
 
     complaint += "</div>"; 

     return complaint; 
}


function remove(ComplaintId, customerId){
	$.ajax({
		type:'delete',
		dataType:'JSON',
		url:'Api/Employee/DeleteCustomerComplaint',
		data:{
			complaintId:ComplaintId,
			CustomerId:customerId
  	    },
		success:function(result){
			if(result.data === "Delete Success"){
			    alert('Delete Success!');
			    
			}else
				alert('Delete Unsuccessful')
		},
		error:function(e){
			alert('Error:'+e);
		}
	});
}

function updateCompletion(customerId, ComplaintNo, bool){
	alert('Completion Called');
	$.ajax({
		type:'post',
		dataType:'JSON',
		url:'Api/Employee/customerComplaintUpdate',
		data:{
			complaintStatus:bool,
			ComplaintNo:ComplaintNo,
			customerId:customerId
  	    },
		success:function(result){
			if(result.data === "Update Success")
			    alert('Update Success!');
			else
				alert('Update Failed')
		},
		error:function(e){
			alert('Error:'+e);
		}
	});
}
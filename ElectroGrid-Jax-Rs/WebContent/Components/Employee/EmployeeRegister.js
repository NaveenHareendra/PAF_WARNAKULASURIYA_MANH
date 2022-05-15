$(document).on('submit', '#target', function( e ) {
	  e.preventDefault();

      
      var name = $("#name").val();
      var email = $("#email").val();
      var address = $("#address").val();
      var password = $("#password").val();
      var passwordCheck = $("#passwordCheck").val();
      var nic = $("#nic").val();
      
//      alert( "Register on progress"+name );
      if(password == passwordCheck && password.length>=7){
          $.ajax({
     	     type:'post',
     	     url:"Api/Employee/Register",
     	     dataType:"JSON",
     	     data:{
     		     empNic:nic,
     		     empName:name,
     		     empAddress:address,
     		     empEmail:email,
     		     empPassword:password
     	     },
     	     success:function(msg){
     	    	 if(msg.data == "Register Success"){
     	    		alert("Registration Success");
     	    	 }else{
     	    		alert("Registration Failed...");
     	    	 }
     		    
     	     },
     	     error:function(e){
     		     alert("Registration Failed, error: "+e);
     	     }
          });    	  
      }else if(password.length<7){
    	  alert("Password you inserted is less than 7 characters");
    	  $( "#passwordCheck" ).replaceWith( "<input class=\"form-control\" type = \"text\" id=\"passwordCheck\" placeholder=\"Retype the Password\" required/>" )
    	  $( "#password" ).replaceWith("<input class=\"form-control\" type = \"text\" id=\"password\" placeholder=\"Insert the Password\" required/>");
      }else{
    	  alert("Re-Typed password invalid");
    	  $( "#passwordCheck" ).replaceWith( "<input class=\"form-control\" type = \"text\" id=\"passwordCheck\" placeholder=\"Retype the Password\" required/>" );
      }

      

});
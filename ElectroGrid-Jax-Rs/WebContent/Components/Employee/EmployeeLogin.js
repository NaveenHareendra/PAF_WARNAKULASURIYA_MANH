$(document).on('submit',  '#Login', function(event){
	event.preventDefault();
	
    var email = $("#email").val();
    var password = $("#password").val();
    
    if(email!=null && password!=null){
    
    	alert( "Login on progress"+name );
    
        $.ajax({
    	    type:'get',
    	    dataType: 'JSON',
    	    url: "Api/Employee/Login/"+email+"/"+password,
    	    success:function(result){
    		alert('Ajax Success:'+result.data.email);
    	},
    	error:function(e){
    		alert('error: '+e);
    	}
        });
    }else{
    	alert('Cannot insert empty fields');
    }
})

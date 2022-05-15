<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
    <link rel="shortcut icon" href="#">
    <link rel="stylesheet" href="view/bootstrap.min.css" />
    <link rel="stylesheet" href="UserInterface/Employee/EmployeeLogin/EmployeeLogin.css"/>
    <script src="Components/jquery-3.2.1.min.js"></script>
        <script src="Components/Employee/EmployeeLogin.js"></script>
</head>
<body>


            <nav class="navbar  navbar-expand-lg navbar-dark bg-dark">
           <div class="container">
           <div class="navbar-brand">
               <h4>
                   Electro-Grid Power
               </h4>
           </div>
           <div class="collapse navbar-collapse  justify-content-center text-light" id="navbarNav">
            <ul class="navbar-nav mr-auto">

                <li class="nav-item active">
                    <a class="nav-link " href="#">Home Page</a>
                </li>
                
                <li class="nav-item active">
                    <a class="nav-link " href="#">Customer Care</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link " href="/ElectroGrid-Jax-Rs/EmployeeComplaints">Customer Complaints</a>
                </li>
                
                <li class="nav-item active">
                    <a class="nav-link " href="/ElectroGrid-Jax-Rs/Login">Employee Login</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link " href="#">Support</a>
                </li>
                
                <li class="nav-item active">
                    <a class="nav-link " href="#">Our Service</a>
                </li>

            </ul>
            </div>
            </div>  
        </nav>
  <div class="container">
  
        <div class="row">
           <div class="col">
           
           </div>
           <div class="col">
               <div class="card" style="width:30rem; margin-top:20px;">
                <div class="card-body" >
                     <div class="row">
                     <div class="card-title">
                         <h4>
                             Employee Login
                         </h4>
                     </div>
                     <hr/>
                     </div>
                     <div class="row">
                     <form id="Login">
                      <div class="form-group">
                         <label>Employee Email:</label>
                         <input class="form-control" type = "email" id="email" placeholder="Insert the email" required/><br/>
                         <label>Employee Password:</label>
                         <input class="form-control" type = "password" id="password" placeholder="Insert the Password" required/><br/>
                       </div>
                       <div class="col" align="center">
                       <input type="submit" value="Login" style="width:11rem;" class="btn btn-primary"/>
                       </div>
                       
                     </form>
                     <br/>
                     </div>
                     <div class="row">
                           <div class="col" align="center">
                              <button onClick="location.href='/ElectroGrid-Jax-Rs/Register'" style="width:11rem; margin-top:10px;" class="btn btn-danger">Employee Register</button>
                           </div>                      
                     </div>

                </div>
            </div>
           </div>
           <div class="col">
           
           </div>
            
        </div>
   </div>
</body>
</html>
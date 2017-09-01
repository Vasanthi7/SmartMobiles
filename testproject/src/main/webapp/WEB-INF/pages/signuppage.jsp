<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="links.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<%@include file="commonnavbar.jsp" %>
<div class="container">
		<div class="col-sm-4" style="background-color: #39c6c6; border-radius:15px; padding-bottom:10px">
		<div align="center" style="color: blue; font-size: 30px">
        <b>Signup page</b>
        </div>


<xyz:form commandName="userobject" action="reqSendSignupDatatoDB" enctype="multipart/form-data">

     <div class="form-group">
          <b>User Name</b> 
          <br>
          <xyz:input path="username" class="form-control" placeholder="Enter User Name"/>
     </div>
     <div class="form-group">
          <b>Password</b>
          <xyz:password path="password" class="form-control" placeholder="Enter Password"/>
     	  <br>
     </div>
     <div class="form-group">
          <b>Email Id </b>
          <br>
          <xyz:input path="emailid" class="form-control"/>
     </div>
     <div class="form-group">
          <b>Mobile</b>
          <br>
          <xyz:input path="Mobile" class="form-control"/>
     </div>
     <div class="form-group">
          <b>Address </b>
          <br>
          <xyz:input path="address" class="form-control"/>
     </div>
     <div class="form-group">
          <b>Browse Image </b>
          <br>
          <xyz:input type="file" path="userImage" class="form-control"/>
     </div>

    <br> 
    <xyz:button>SignUp</xyz:button>
</xyz:form>
</div>
</div>
</body>
</html>
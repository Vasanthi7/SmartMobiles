<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="links.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@include file="adminnavbar.jsp" %>
<br>
<div class="container">
		<div class="col-sm-4" style="background-color: #39c6c6; border-radius:15px; padding-bottom:10px">
		<div align="center" style="color: blue; font-size: 30px">
        <b>Edit Product page</b>
        </div>

<xyz:form commandName="supplier" action="reqUpdateSupplierData" enctype="multipart/form-data">

<xyz:hidden path="supplierid"/>


     <div class="form-group">
          <b>supplier name</b><br>
          <xyz:input path="suppliername" class="form-control" placeholder="enter product name"/>
     </div>
     
     
     <div class="form-group">
          <b>supplier desc</b><br>
          <xyz:input path="supplierdesc" class="form-control" placeholder="enter product desc"/>
     </div>
 
 
 
	 <div class="form-group">
          <b>Supplier Available</b><br>
         True<xyz:radiobutton path="issupplieravailable" value="true"/>
         False<xyz:radiobutton path="issupplieravailable" value="false"/>
     </div>
     
     

<br><xyz:button>Modify Supplier</xyz:button>
</xyz:form>


</body>
</html>
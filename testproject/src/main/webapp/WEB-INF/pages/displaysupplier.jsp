<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="links.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>

$(document).ready(function(){
	var searchCondition='${searchCondition}';
	$('.table').DataTable({
		"lengthMenu" : [[5,10,15,-1],[5,10,15,"All"]],
		"oSearch":{"sSearch":searchCondition}		
	})	
});

</script>
</head>
<body>
${suppliers}
display supplier

<%@include file="adminnavbar.jsp" %>

<br>
<div class="container">

<table class="table table-stripped fixed_headers">
<thead>
<tr>
<th>Supplier ID </th>
<th>Image</th>
<th>Supplier Name</th>
<th>Supplier Desc</th>
<th>Supplier Available</th>
<th>Options</th>
</tr>
</thead>

<tbody>
<c:forEach var="su" items="${suppliers}">
<tr>
<td>${su.supplierid}</td>
<td><img src="resources/images/supplier/${su.supplierid}.jpg" height="100px" width="150px"/></td>
<td>${su.suppliername}</td>
<td>${su.supplierdesc}</td>
<td>${su.issupplieravailable}</td>

<td>
<a href="reqEditSupplier?sid=${su.supplierid}">  Edit   </a> 
/
<a href="reqDeleteSupplier?sid=${su.supplierid}"> Delete  </a>

</td>
</tr>
</c:forEach>


</tbody>

</table>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="links.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
      width: 100%;
      margin: auto;
      height:400px;
  }
   .carousel-control.left, .carousel-control.right {
  background-image: none;opacity=0.4;
</style>
</head>
<body>
<%@include file="commonnavbar.jsp" %>

<c:if test="${message!=null}">
	<div class="container">
    
    <div class="col-sm-3"></div>
    <div class="col-sm-6">
     <div class="alert alert-success alert-dismissable fade in">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
    ${message}
    </div>
  </div>
  </div>
  </c:if>
<br>
<div class="container">
 <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
      <div class="item active">
        <img src="resources/images/carousel/BlackBerry.jpg" alt="WildCart" style="width:50%;">
      </div>

      <div class="item">
        <img src="resources/images/carousel/microsoft.jpg" alt="UrbanTribe" style="width:50%;">
      </div>
    
      <div class="item">
        <img src="resources/images/carousel/Sony.jpg" alt="Puma" style="width:50%;">
      </div>
      
      
       <div class="item">
        <img src="resources/images/carousel/lenovo1.gif" alt="Mobile" style="width:50%;">
      </div>
      
      <div class="item">
        <img src="resources/images/carousel/vivo Y55s.jpeg" alt="Mobile" style="width:50%;">
      </div>
      
      
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>
</div>

</body>
</html>
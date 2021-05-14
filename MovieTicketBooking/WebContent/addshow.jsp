<%
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", -1); 

if (session != null) {
    if (session.getAttribute("user") == null) {
    	response.sendRedirect("index.jsp");
    }
}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Movie Show</title>
<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function () {
		$.ajax({
		    type: "GET",
		    url: "MovieController",
		    contentType: "application/json",
		    async: false,
		    success: function (data) {
		    	for (var key in data) {
		    		$("#drop-down").append("<option value='"+data[key]+"'>"+key+"</option>"); 
		    	}
		    }
	    });
	});
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/ShowController" method="post" name="addShow">
		Select Movie: <select name='movieId' id="drop-down"></select><br>
		Enter Show Name:	<input type="text" name="name" placeholder="Morning Show" /> <br>
		Enter Start Time:	<input type="text" name="start" placeholder="14/5/2021 6:00 AM" /> <br>
		Enter End Time:	<input type="text" name="end" placeholder="14/5/2021 8:00 AM"/> <br>
		Enter Price:	<input type="text" name="price" placeholder="200.00"/> <br>
		<input type="submit" name="submit" value="Add" />
	</form>
</body>
</html>
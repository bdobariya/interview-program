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
<title>Add Movie</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/MovieController" method="post" name="addMovie">
		Enter Movie Name:	<input type="text" name="name" /> <br>
		Enter Movie Length:	<input type="text" name="length" /> <br>
		<input type="submit" name="submit" value="Add" />
	</form>
</body>
</html>
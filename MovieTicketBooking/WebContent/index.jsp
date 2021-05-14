<%
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", -1); 
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login - Movie Ticket Booking</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/LoginController" method="get" name="loginPage">
		Enter email: <input type="text" name="email"/><br>
		Enter password: <input type="password" name="password"/><br>
		<input type="submit" name="submit" value="Login" />
	</form>
</body>
</html>
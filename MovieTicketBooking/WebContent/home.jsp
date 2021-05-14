<%
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", -1); 
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Movie Ticket Booking System</title>
</head>
<body>
   <%
      if (session != null) {
         if (session.getAttribute("user") != null) {
            String email = (String) session.getAttribute("user");
            out.print("Hello, " + email + "  Welcome to Movie Ticket Booking System");
            
            if(email.equals("admin.user@gmail.com")) {
    %>
    			<br/>
    			<a href="addmovie.jsp">Add Movie</a><br/>
    			<a href="addshow.jsp">Add Show</a><br/>
    <%        	
            } else {
	%>
				<br/>
				<a href="bookticket.jsp">Book Ticket</a>	
	<%            	
            }
            
         } else {
            response.sendRedirect("index.jsp");
         }
      }
   %>
</body>
</html>
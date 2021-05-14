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
<title>Book the Movie Ticket</title>
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
		    		$("#drop-down-movie").append("<option value='"+data[key]+"'>"+key+"</option>"); 
		    	}
		    }
	    });
		
		$.ajax({
		    type: "GET",
		    url: "ShowController",
		    data:{operation:'getShows'},
		    contentType: "application/json",
		    async: false,
		    success: function (data) {
		    	for (var key in data) {
		    		$("#drop-down-shows").append("<option value='"+key+"'>"+data[key]+"</option>"); 
		    	}
		    }
	    });
	});
	
	function getPriceByShow(showid) {
		$.ajax({
		    type: "GET",
		    url: "ShowController",
		    data:{operation:'getPriceByShows',id:showid.value},
		    contentType: "application/json",
		    async: false,
		    success: function (data) {
		    	$("#h_price").text(data); 
		    	$("#price").val(data); 
		    }
	    });
		
	}
	
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/TicketController" method="post" name="bookTicket">
		Select Movie: <select name='movieId' id="drop-down-movie"><option>--Select Movie--</option></select><br>
		Select Shows: <select name='showsId' id="drop-down-shows" onchange="getPriceByShow(this);"><option>--Select Today Shows Only--</option></select><br>
		Enter Name:	<input type="text" name="name" placeholder="Brijesh Dobariya" /> <br>
		Price:	<span id="h_price">0.00</span> <br>
		<input type="hidden" name="price" id="price" value="0.00" />
		<input type="submit" name="submit" value="Add" />
	</form>
</body>
</html>
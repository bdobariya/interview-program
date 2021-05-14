package com.ticket.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ticket.entities.UserDetail;
import com.ticket.services.UserService;
import com.ticket.services.UserServiceImpl;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
		UserService userService = new UserServiceImpl();
		UserDetail userDetail = userService.findUserByEmailAndPassword(email, pwd);
		
		if (userDetail != null) {
			out.print("Welcome, " + email);
			HttpSession session = request.getSession(true); // reuse existing, session if exist or create one
			session.setAttribute("user", email);
			response.sendRedirect("home.jsp");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			out.println("<font color=red>eMail and Password are invalid.</font>");
			rd.include(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

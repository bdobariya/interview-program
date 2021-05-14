package com.ticket.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.ticket.entities.MovieDetail;
import com.ticket.services.MovieService;
import com.ticket.services.MovieServiceImpl;

@WebServlet(name = "MovieController", urlPatterns = {"/MovieController"})
public class MovieController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		MovieService movieService = new MovieServiceImpl();
		Map<String, MovieDetail> map = movieService.findAll();
		Map<String, Integer> newMap = new HashMap<>();
		for(Map.Entry<String, MovieDetail> list : map.entrySet())
		{
			newMap.put(list.getKey(), list.getValue().getId());
		}
		response.setContentType("application/json");
		Gson json = new Gson();
		response.getWriter().write(json.toJson(newMap));
		response.getWriter().flush();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null && session.getAttribute("user").equals("admin.user@gmail.com")) 
		{
			String name = request.getParameter("name");
			int length = Integer.parseInt(request.getParameter("length"));
			MovieDetail movieDetail = new MovieDetail();
			movieDetail.setName(name);
			movieDetail.setLength(length);
			MovieService movieService = new MovieServiceImpl();
			movieService.addMovie(movieDetail);
			
			System.out.println(movieService.findAll());
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			out.println("<font color=red>Access Restricted Area.</font>");
			rd.include(request, response);
		}
	}

}

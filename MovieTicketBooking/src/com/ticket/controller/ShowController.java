package com.ticket.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.ticket.data.DummyData;
import com.ticket.entities.ShowDetail;
import com.ticket.services.ShowService;
import com.ticket.services.ShowServiceImpl;

@WebServlet(name = "ShowController", urlPatterns = {"/ShowController"})
public class ShowController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ShowService showService = new ShowServiceImpl();
		List<ShowDetail> todayShows = showService.findShowByCurrentDate(new Date());
		if(request.getParameter("operation").equals("getShows")) {
			Map<Integer, String> map = new HashMap<>();
			for(ShowDetail showDetail : todayShows) {
				map.put(showDetail.getId(), showDetail.getName()+" - "+showDetail.getStartDateTime()+" - "+showDetail.getEndDateTime());
			}
			response.setContentType("application/json");
			Gson json = new Gson();
			response.getWriter().write(json.toJson(map));
			response.getWriter().flush();
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			for(ShowDetail showDetail : todayShows) {
				if(showDetail.getId()==id) {
					response.getWriter().write(String.valueOf(showDetail.getPrice()));
					response.getWriter().flush();
				}
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null && session.getAttribute("user").equals("admin.user@gmail.com")) 
		{
			int movieId = Integer.parseInt(request.getParameter("movieId"));
			String name = request.getParameter("name");
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			float price = Float.parseFloat(request.getParameter("price"));
			
			SimpleDateFormat formatter = new SimpleDateFormat(DummyData.DATE_FORMAT);
			try {
				Date startDtTime = formatter.parse(start);
				Date endDtTime = formatter.parse(end);
				
				ShowDetail showDetail = new ShowDetail();
				showDetail.setMovieId(movieId);
				showDetail.setName(name);
				showDetail.setStartDateTime(startDtTime);
				showDetail.setEndDateTime(endDtTime);
				showDetail.setPrice(price);
				
				ShowService showService = new ShowServiceImpl();
				showService.addShow(showDetail);
				
				System.out.println("Number of Shows on Same Day : "+showService.findShowByCurrentDate(new Date()));
				System.out.println("All Shows : "+showService.findAll());
				
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
			
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			out.println("<font color=red>Access Restricted Area.</font>");
			rd.include(request, response);
		}
		
	}

}

package com.ticket.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.glass.ui.Application;
import com.ticket.data.ApplicationCache;
import com.ticket.entities.ShowDetail;
import com.ticket.entities.TicketDetail;
import com.ticket.services.ShowService;
import com.ticket.services.ShowServiceImpl;
import com.ticket.services.TicketBookingService;
import com.ticket.services.TicketBookingServiceImpl;

@WebServlet(name = "TicketController", urlPatterns = {"/TicketController"})
public class TicketController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		int showId = Integer.parseInt(request.getParameter("showsId"));
		String name = request.getParameter("name");
		float price = Float.parseFloat(request.getParameter("price")); 
		
		ShowService showService = new ShowServiceImpl();
		List<ShowDetail> todayShows = showService.findShowByCurrentDate(new Date());
		for(int index = 0; index < todayShows.size(); index++) {
			ShowDetail showDetail = todayShows.get(index);
			if(showDetail.getId() == showId) {
				if(showDetail.getBookedTicket() < 30) {
					
					TicketDetail ticketDetail = new TicketDetail();
					ticketDetail.setMovieId(movieId);
					ticketDetail.setShowId(showId);
					ticketDetail.setCustomerName(name);
					ticketDetail.setPrice(price);
					
					TicketBookingService ticketBookingService = new TicketBookingServiceImpl();
					ticketBookingService.bookTicket(ticketDetail);
					
					List<ShowDetail> newList = showService.findAll();
					System.out.println("Before = "+newList);
					ApplicationCache.getInstance().clearCache(ApplicationCache.CACHE_SHOWDETAIL_DATA);
					
					for(ShowDetail showDetailFrmNew : newList) {
						if(showDetailFrmNew.getId() == showDetail.getId()) {
							showDetail.setBookedTicket(showDetail.getBookedTicket()+1);
							showService.addShow(showDetail);
						} else {
							showService.addShow(showDetailFrmNew);
						}
					}
					
					System.out.println("After = "+showService.findShowByCurrentDate(new Date()));
					System.out.println("After = "+showService.findAll());
				} else {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					RequestDispatcher rd = request.getRequestDispatcher("bookticket.jsp");
					out.println("<font color=red>Show is overflow. Maximum limit is over.</font>");
					rd.include(request, response);
				}
			}
		}
		
	}

}

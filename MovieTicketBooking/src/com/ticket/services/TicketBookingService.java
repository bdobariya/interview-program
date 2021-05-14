package com.ticket.services;

import java.util.List;

import com.ticket.entities.TicketDetail;

public interface TicketBookingService {

	public boolean bookTicket(TicketDetail ticketDetail);
	public List<TicketDetail> findAll();
	
}

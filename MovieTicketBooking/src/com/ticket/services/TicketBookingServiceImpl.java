package com.ticket.services;

import java.util.List;

import com.ticket.data.ApplicationCache;
import com.ticket.entities.TicketDetail;

public class TicketBookingServiceImpl implements TicketBookingService {

	@Override
	public boolean bookTicket(TicketDetail ticketDetail) {
		ApplicationCache.getInstance().addTicketData(ticketDetail);
		return true;
	}

	@Override
	public List<TicketDetail> findAll() {
		return ApplicationCache.getInstance().getTicketData();
	}

}

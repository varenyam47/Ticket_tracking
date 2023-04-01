package com.Ticket_Tracking.service;

import java.util.List;

import com.Ticket_Tracking.entity.Ticket;
public interface TicketService {

	List<Ticket> getAllTickets();

	Ticket saveTicket(Ticket ticket);
	
	Ticket getTicketById(Long id);
	
	Ticket viewTicket(Long id);

	Ticket updateTicket(Ticket ticket);
	
	
	void deleteTicketById(Long id);

	List<Ticket> getByKeyword(String keyword);

}

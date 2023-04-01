package com.Ticket_Tracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import com.Ticket_Tracking.entity.Ticket;
import com.Ticket_Tracking.service.TicketService;
@Controller
public class TicketController {

	@Autowired
	private TicketService ticketService;

	public TicketController(TicketService ticketService) {
		super();
		this.ticketService = ticketService;
	}

	@RequestMapping("/tickets")
	public String showTickets(Model model) {
	    // add data to the model
	    return "ticketsViewName"; // specify the view name here
	}


	@GetMapping("/tickets/new")
	public String createticketForm(Model model) {
		Ticket ticket = new Ticket();
		model.addAttribute("ticket", ticket);
		return "create_ticket";
	}

	@PostMapping("/tickets")
	public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
		ticketService.saveTicket(ticket);
		return "redirect:/tickets";
	}

	@GetMapping("tickets/edit/{id}")
	public String editTicket(@PathVariable Long id, Model model) {
		model.addAttribute("ticket", ticketService.getTicketById(id));
		return "edit_ticket";
	}

	@PostMapping("/tickets/{id}")
	public String updateTicket(@PathVariable Long id, @ModelAttribute("ticket") Ticket ticket, Model model) {
		Ticket existingTicket = ticketService.getTicketById(id);
		existingTicket.setId(id);
		existingTicket.setTicketTitle(ticket.getTicketTitle());
		existingTicket.setTicketShortDescription(ticket.getTicketShortDescription());
		existingTicket.setTicketCreatedOn(ticket.getTicketCreatedOn());
		ticketService.updateTicket(existingTicket);
		return "redirect:/tickets";
	}

	@GetMapping("tickets/view/{id}")
	public String viewticketForm(@PathVariable Long id, Model model) {
		model.addAttribute("ticket", ticketService.getTicketById(id));
		return "view_ticket";
	}

	@PostMapping("/tickets/view/{id}")
	public String viewTicket(@PathVariable Long id, @ModelAttribute("ticket") Ticket ticket, Model model) {
		Ticket viewTicket = ticketService.getTicketById(id);
		viewTicket.setId(id);
		viewTicket.setTicketTitle(ticket.getTicketTitle());
		viewTicket.setTicketShortDescription(ticket.getTicketShortDescription());
		viewTicket.setTicketCreatedOn(ticket.getTicketCreatedOn());

		ticketService.viewTicket(id);
		return "redirect:/tickets";
	}

	@GetMapping("/tickets/{id}")
	public String deleteticket(@PathVariable Long id) {
		ticketService.deleteTicketById(id);
		return "redirect:/tickets";
	}
	
	@RequestMapping("/search")
	public String home(Ticket ticket, Model model, String keyword) {
		if(keyword!=null) {
			List<Ticket> list=ticketService.getByKeyword(keyword);
			model.addAttribute("list", list);
		}
		else {
			List<Ticket> list=ticketService.getAllTickets();
			model.addAttribute("list",list);
		}
		return "search_ticket";
	}

}
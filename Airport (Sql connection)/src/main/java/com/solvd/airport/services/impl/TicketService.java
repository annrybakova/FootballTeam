package com.solvd.airport.services.impl;

import com.solvd.airport.dao.interfaces.ITicketDAO;
import com.solvd.airport.models.Ticket;

public class TicketService {
    private ITicketDAO ticketDAO;

    public TicketService(ITicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public void bookTicket(Ticket ticket) {
        ticketDAO.add(ticket);
    }
}


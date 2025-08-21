package com.solvd.airport.services.impl;

import com.solvd.airport.dao.interfaces.TicketDAO;
import com.solvd.airport.models.Ticket;

public class TicketService {
    private TicketDAO ticketDAO;

    public TicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public void bookTicket(Ticket ticket) {
        ticketDAO.add(ticket);
    }
}


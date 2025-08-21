package com.solvd.airport.services.impl;


import com.solvd.airport.dao.interfaces.PassengerDAO;
import com.solvd.airport.dao.interfaces.TicketDAO;
import com.solvd.airport.dao.interfaces.LuggageDAO;
import com.solvd.airport.models.Luggage;
import com.solvd.airport.models.Passenger;


public class PassengerService<LuggageDAO> {
    private PassengerDAO passengerDAO;
    private TicketDAO ticketDAO;
    private LuggageDAO luggageDAO;

    public PassengerService(PassengerDAO passengerDAO, TicketDAO ticketDAO, LuggageDAO luggageDAO) {
        this.passengerDAO = passengerDAO;
        this.ticketDAO = ticketDAO;
        this.luggageDAO = luggageDAO;
    }

    public void registerPassenger(Passenger passenger) {
        passengerDAO.add(passenger);
    }
}



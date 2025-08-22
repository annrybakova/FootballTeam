package com.solvd.airport.services.impl;


import com.solvd.airport.dao.interfaces.IPassengerDAO;
import com.solvd.airport.dao.interfaces.ITicketDAO;
import com.solvd.airport.dao.interfaces.ILuggageDAO;
import com.solvd.airport.models.Luggage;
import com.solvd.airport.models.Passenger;


public class PassengerService<LuggageDAO> {
    private IPassengerDAO passengerDAO;
    private ITicketDAO ticketDAO;
    private ILuggageDAO luggageDAO;

    public PassengerService(IPassengerDAO passengerDAO, ITicketDAO ticketDAO, ILuggageDAO luggageDAO) {
        this.passengerDAO = passengerDAO;
        this.ticketDAO = ticketDAO;
        this.luggageDAO = luggageDAO;
    }

    public void registerPassenger(Passenger passenger) {
        passengerDAO.add(passenger);
    }
}



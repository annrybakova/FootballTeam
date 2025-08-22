package com.solvd.airport.services.impl;

import com.solvd.airport.dao.interfaces.IAirlineDAO;
import com.solvd.airport.dao.interfaces.IFlightDAO;
import com.solvd.airport.models.Flight;

public class FlightService {
    private IFlightDAO flightDAO;
    private IAirlineDAO airlineDAO;

    public FlightService(IFlightDAO flightDAO, IAirlineDAO airlineDAO) {
        this.flightDAO = flightDAO;
        this.airlineDAO = airlineDAO;
    }

    public void scheduleFlight(Flight flight) {
        flightDAO.add(flight);
    }
}


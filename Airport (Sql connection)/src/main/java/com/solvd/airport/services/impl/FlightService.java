package com.solvd.airport.services.impl;

import com.solvd.airport.dao.interfaces.AirlineDAO;
import com.solvd.airport.dao.interfaces.FlightDAO;
import com.solvd.airport.models.Flight;

public class FlightService {
    private FlightDAO flightDAO;
    private AirlineDAO airlineDAO;

    public FlightService(FlightDAO flightDAO, AirlineDAO airlineDAO) {
        this.flightDAO = flightDAO;
        this.airlineDAO = airlineDAO;
    }

    public void scheduleFlight(Flight flight) {
        flightDAO.add(flight);
    }
}


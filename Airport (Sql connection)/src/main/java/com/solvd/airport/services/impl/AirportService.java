package com.solvd.airport.services.impl;

import com.solvd.airport.dao.interfaces.IAirportDAO;
import com.solvd.airport.models.Airport;

public class AirportService {
    private IAirportDAO airportDAO;

    public AirportService(IAirportDAO airportDAO) {
        this.airportDAO = airportDAO;
    }

    public void addAirport(Airport airport) {
        airportDAO.add(airport);
    }
}


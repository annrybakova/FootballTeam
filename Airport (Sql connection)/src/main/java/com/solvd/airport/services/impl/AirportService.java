package com.solvd.airport.services.impl;

import com.solvd.airport.dao.interfaces.AirportDAO;
import com.solvd.airport.models.Airport;

public class AirportService {
    private AirportDAO airportDAO;

    public AirportService(AirportDAO airportDAO) {
        this.airportDAO = airportDAO;
    }

    public void addAirport(Airport airport) {
        airportDAO.add(airport);
    }
}


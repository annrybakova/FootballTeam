package com.solvd.airport.dao.interfaces;

import java.util.List;

import com.solvd.airport.models.Airline;

public interface AirlineDAO {
    void add(Airline airline);
    Airline getById(int id);
    List<Airline> getAll();
    void update(Airline airline);
    void delete(int id);
}


package com.solvd.airport.dao.interfaces;

import java.util.List;

import com.solvd.airport.models.Airport;

public interface IAirportDAO {
    void add(Airport airport);
    Airport getById(int id);
    List<Airport> getAll();
    void update(Airport airport);
    void delete(int id);
}

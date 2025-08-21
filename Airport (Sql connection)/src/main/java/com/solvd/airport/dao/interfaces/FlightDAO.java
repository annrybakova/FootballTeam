package com.solvd.airport.dao.interfaces;

import com.solvd.airport.models.Flight;
import java.util.List;

public interface FlightDAO {
    void add(Flight flight);
    Flight getById(int id);
    List<Flight> getAll();
    void update(Flight flight);
    void delete(int id);
}


package com.solvd.airport.dao.interfaces;


import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.models.Passenger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface PassengerDAO {
    void add(Passenger passenger);
    Passenger getById(int id);
    List<Passenger> getAll();
    void update(Passenger passenger);
    void delete(int id);
}


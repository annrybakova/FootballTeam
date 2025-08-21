package com.solvd.airport.dao.interfaces;

import java.util.List;

import com.solvd.airport.models.Luggage;

public interface LuggageDAO {
    void add(Luggage luggage);
    Luggage getById(int id);
    List<Luggage> getAll();
    void update(Luggage luggage);
    void delete(int id);
}


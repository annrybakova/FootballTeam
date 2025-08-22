package com.solvd.airport.dao.interfaces;

import java.util.List;

import com.solvd.airport.models.Ticket;

public interface ITicketDAO {
    void add(Ticket ticket);
    Ticket getById(int id);
    List<Ticket> getAll();
    void update(Ticket ticket);
    void delete(int id);
}

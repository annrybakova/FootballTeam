package com.solvd.airport.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.interfaces.TicketDAO;
import com.solvd.airport.models.Ticket;

public class ITicketDAO implements TicketDAO {
    private static final String T = "tickets";
    private static final String ID = "ticket_id";
    private static final String PASSENGER_ID = "passenger_id";
    private static final String FLIGHT_ID = "flight_id";
    private static final String SEAT = "seat_number";
    private static final String PRICE = "price";

    @Override public void add(Ticket t) {
        String sql = "INSERT INTO " + T + " (" + PASSENGER_ID + "," + FLIGHT_ID + "," + SEAT + "," + PRICE + ") VALUES (?,?,?,?)";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, t.getPassengerId());
            ps.setInt(2, t.getFlightId());
            ps.setString(3, t.getSeatNumber());
            ps.setDouble(4, t.getPrice());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) t.setTicketId(rs.getInt(1));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
    @Override public Ticket getById(int id) {
        String sql = "SELECT * FROM " + T + " WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Ticket t = new Ticket();
                    t.setTicketId(rs.getInt(ID));
                    t.setPassengerId(rs.getInt(PASSENGER_ID));
                    t.setFlightId(rs.getInt(FLIGHT_ID));
                    t.setSeatNumber(rs.getString(SEAT));
                    t.setPrice(rs.getDouble(PRICE));
                    return t;
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }
    @Override public List<Ticket> getAll() {
        String sql = "SELECT * FROM " + T;
        List<Ticket> list = new ArrayList<>();
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ticket t = new Ticket();
                t.setTicketId(rs.getInt(ID));
                t.setPassengerId(rs.getInt(PASSENGER_ID));
                t.setFlightId(rs.getInt(FLIGHT_ID));
                t.setSeatNumber(rs.getString(SEAT));
                t.setPrice(rs.getDouble(PRICE));
                list.add(t);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }
    @Override public void update(Ticket t) {
        String sql = "UPDATE " + T + " SET " + PASSENGER_ID + "=?," + FLIGHT_ID + "=?," + SEAT + "=?," + PRICE + "=? WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, t.getPassengerId());
            ps.setInt(2, t.getFlightId());
            ps.setString(3, t.getSeatNumber());
            ps.setDouble(4, t.getPrice());
            ps.setInt(5, t.getTicketId());
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
    @Override public void delete(int id) {
        String sql = "DELETE FROM " + T + " WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}

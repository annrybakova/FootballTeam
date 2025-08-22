package com.solvd.airport.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.interfaces.IFlightDAO;
import com.solvd.airport.models.Flight;

public class FlightDAO implements IFlightDAO {
    private static final String T = "flights";
    private static final String ID = "flight_id";
    private static final String AIRLINE_ID = "airline_id";
    private static final String FROM_AP = "departure_airport_id";
    private static final String TO_AP = "arrival_airport_id";
    private static final String DEP = "departure_time";
    private static final String ARR = "arrival_time";

    @Override public void add(Flight f) {
        String sql = "INSERT INTO " + T + " (" + AIRLINE_ID + "," + FROM_AP + "," + TO_AP + "," + DEP + "," + ARR + ") VALUES (?,?,?,?,?)";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, f.getAirlineId());
            ps.setInt(2, f.getDepartureAirportId());
            ps.setInt(3, f.getArrivalAirportId());
            ps.setTimestamp(4, Timestamp.valueOf(f.getDepartureTime()));
            ps.setTimestamp(5, Timestamp.valueOf(f.getArrivalTime()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) f.setFlightId(rs.getInt(1));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
    @Override public Flight getById(int id) {
        String sql = "SELECT * FROM " + T + " WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }
    @Override public List<Flight> getAll() {
        String sql = "SELECT * FROM " + T;
        List<Flight> list = new ArrayList<>();
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }
    @Override public void update(Flight f) {
        String sql = "UPDATE " + T + " SET " + AIRLINE_ID + "=?," + FROM_AP + "=?," + TO_AP + "=?," + DEP + "=?," + ARR + "=? WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, f.getAirlineId());
            ps.setInt(2, f.getDepartureAirportId());
            ps.setInt(3, f.getArrivalAirportId());
            ps.setTimestamp(4, Timestamp.valueOf(f.getDepartureTime()));
            ps.setTimestamp(5, Timestamp.valueOf(f.getArrivalTime()));
            ps.setInt(6, f.getFlightId());
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
    private Flight map(ResultSet rs) throws SQLException {
        Flight f = new Flight();
        f.setFlightId(rs.getInt(ID));
        f.setAirlineId(rs.getInt(AIRLINE_ID));
        f.setDepartureAirportId(rs.getInt(FROM_AP));
        f.setArrivalAirportId(rs.getInt(TO_AP));
        f.setDepartureTime(rs.getTimestamp(DEP).toLocalDateTime());
        f.setArrivalTime(rs.getTimestamp(ARR).toLocalDateTime());
        return f;
    }
}

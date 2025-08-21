package com.solvd.airport.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.interfaces.AirportDAO;
import com.solvd.airport.models.Airport;

public class IAirportDAO implements AirportDAO {
    private static final String T = "airports";
    private static final String ID = "airport_id";
    private static final String NAME = "name";
    private static final String CITY = "city";
    private static final String COUNTRY = "country";

    @Override public void add(Airport a) {
        String sql = "INSERT INTO " + T + " (" + NAME + "," + CITY + "," + COUNTRY + ") VALUES (?,?,?)";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, a.getName());
            ps.setString(2, a.getCity());
            ps.setString(3, a.getCountry());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) a.setAirportId(rs.getInt(1));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
    @Override public Airport getById(int id) {
        String sql = "SELECT " + ID + "," + NAME + "," + CITY + "," + COUNTRY + " FROM " + T + " WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Airport a = new Airport();
                    a.setAirportId(rs.getInt(ID));
                    a.setName(rs.getString(NAME));
                    a.setCity(rs.getString(CITY));
                    a.setCountry(rs.getString(COUNTRY));
                    return a;
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }
    @Override public List<Airport> getAll() {
        String sql = "SELECT " + ID + "," + NAME + "," + CITY + "," + COUNTRY + " FROM " + T;
        List<Airport> list = new ArrayList<>();
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Airport a = new Airport();
                a.setAirportId(rs.getInt(ID));
                a.setName(rs.getString(NAME));
                a.setCity(rs.getString(CITY));
                a.setCountry(rs.getString(COUNTRY));
                list.add(a);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }
    @Override public void update(Airport a) {
        String sql = "UPDATE " + T + " SET " + NAME + "=?," + CITY + "=?," + COUNTRY + "=? WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, a.getName());
            ps.setString(2, a.getCity());
            ps.setString(3, a.getCountry());
            ps.setInt(4, a.getAirportId());
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
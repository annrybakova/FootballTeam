package com.solvd.airport.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.interfaces.AirlineDAO;
import com.solvd.airport.models.Airline;

public class IAirlineDAO implements AirlineDAO {
    private static final String T = "airlines";
    private static final String ID = "airline_id";
    private static final String NAME = "name";

    @Override public void add(Airline a) {
        String sql = "INSERT INTO " + T + " (" + NAME + ") VALUES (?)";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, a.getName());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) a.setAirlineId(rs.getInt(1));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
    @Override public Airline getById(int id) {
        String sql = "SELECT " + ID + "," + NAME + " FROM " + T + " WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Airline a = new Airline();
                    a.setAirlineId(rs.getInt(ID));
                    a.setName(rs.getString(NAME));
                    return a;
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }
    @Override public List<Airline> getAll() {
        String sql = "SELECT " + ID + "," + NAME + " FROM " + T;
        List<Airline> list = new ArrayList<>();
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Airline a = new Airline();
                a.setAirlineId(rs.getInt(ID));
                a.setName(rs.getString(NAME));
                list.add(a);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }
    @Override public void update(Airline a) {
        String sql = "UPDATE " + T + " SET " + NAME + "=? WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, a.getName());
            ps.setInt(2, a.getAirlineId());
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


package com.solvd.airport.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.interfaces.IPassengerDAO;
import com.solvd.airport.models.Passenger;

public class PassengerDAO implements IPassengerDAO {
    private static final String T = "passengers";
    private static final String ID = "passenger_id";
    private static final String NAME = "name";

    @Override public void add(Passenger p) {
        String sql = "INSERT INTO " + T + " (" + NAME + ") VALUES (?)";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getName());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setPassengerId(rs.getInt(1));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
    @Override public Passenger getById(int id) {
        String sql = "SELECT " + ID + "," + NAME + " FROM " + T + " WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Passenger p = new Passenger();
                    p.setPassengerId(rs.getInt(ID));
                    p.setName(rs.getString(NAME));
                    return p;
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }
    @Override public List<Passenger> getAll() {
        String sql = "SELECT " + ID + "," + NAME + " FROM " + T;
        List<Passenger> list = new ArrayList<>();
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Passenger p = new Passenger();
                p.setPassengerId(rs.getInt(ID));
                p.setName(rs.getString(NAME));
                list.add(p);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }
    @Override public void update(Passenger p) {
        String sql = "UPDATE " + T + " SET " + NAME + "=? WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setInt(2, p.getPassengerId());
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

package com.solvd.airport.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;
import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.interfaces.LuggageDAO;
import com.solvd.airport.models.Luggage;

public class ILuggageDAO implements LuggageDAO {
    private static final String T = "luggage";
    private static final String ID = "luggage_id";
    private static final String PASSENGER_ID = "passenger_id";
    private static final String WEIGHT = "weight";

    @Override
    public void add(Luggage l) {
        String sql = "INSERT INTO " + T + " (" + PASSENGER_ID + "," + WEIGHT + ") VALUES (?,?)";
        try (Connection c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, l.getPassengerId());
            ps.setDouble(2, l.getWeight());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next())
                    l.setLuggageId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Luggage getById(int id) {
        String sql = "SELECT * FROM " + T + " WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Luggage l = new Luggage();
                    l.setLuggageId(rs.getInt(ID));
                    l.setPassengerId(rs.getInt(PASSENGER_ID));
                    l.setWeight(rs.getDouble(WEIGHT));
                    return l;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Luggage> getAll() {
        String sql = "SELECT * FROM " + T;
        List<Luggage> list = new ArrayList<>();
        try (Connection c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Luggage l = new Luggage();
                l.setLuggageId(rs.getInt(ID));
                l.setPassengerId(rs.getInt(PASSENGER_ID));
                l.setWeight(rs.getDouble(WEIGHT));
                list.add(l);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void update(Luggage l) {
        String sql = "UPDATE " + T + " SET " + PASSENGER_ID + "=?," + WEIGHT + "=? WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, l.getPassengerId());
            ps.setDouble(2, l.getWeight());
            ps.setInt(3, l.getLuggageId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM " + T + " WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

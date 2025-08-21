package com.solvd.airport.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.interfaces.StaffDAO;
import com.solvd.airport.models.Staff;

public class IStaffDAO implements StaffDAO {
    private static final String T = "staff";
    private static final String ID = "staff_id";
    private static final String ROLE_ID = "role_id";
    private static final String NAME = "name";

    @Override public void add(Staff s) {
        String sql = "INSERT INTO " + T + " (" + ROLE_ID + "," + NAME + ") VALUES (?,?)";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, s.getRoleId());
            ps.setString(2, s.getName());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) s.setStaffId(rs.getInt(1));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
    @Override public Staff getById(int id) {
        String sql = "SELECT * FROM " + T + " WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Staff s = new Staff();
                    s.setStaffId(rs.getInt(ID));
                    s.setRoleId(rs.getInt(ROLE_ID));
                    s.setName(rs.getString(NAME));
                    return s;
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }
    @Override public List<Staff> getAll() {
        String sql = "SELECT * FROM " + T;
        List<Staff> list = new ArrayList<>();
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Staff s = new Staff();
                s.setStaffId(rs.getInt(ID));
                s.setRoleId(rs.getInt(ROLE_ID));
                s.setName(rs.getString(NAME));
                list.add(s);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }
    @Override public void update(Staff s) {
        String sql = "UPDATE " + T + " SET " + ROLE_ID + "=?, " + NAME + "=? WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, s.getRoleId());
            ps.setString(2, s.getName());
            ps.setInt(3, s.getStaffId());
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
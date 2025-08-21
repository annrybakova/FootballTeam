package com.solvd.airport.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.interfaces.RoleDAO;
import com.solvd.airport.models.Role;

public class IRoleDAO implements RoleDAO {
    private static final String T = "roles";
    private static final String ID = "role_id";
    private static final String NAME = "role_name";

    @Override public void add(Role r) {
        String sql = "INSERT INTO " + T + " (" + NAME + ") VALUES (?)";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, r.getRoleName());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) r.setRoleId(rs.getInt(1));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
    @Override public Role getById(int id) {
        String sql = "SELECT " + ID + "," + NAME + " FROM " + T + " WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Role r = new Role();
                    r.setRoleId(rs.getInt(ID));
                    r.setRoleName(rs.getString(NAME));
                    return r;
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }
    @Override public List<Role> getAll() {
        String sql = "SELECT " + ID + "," + NAME + " FROM " + T;
        List<Role> list = new ArrayList<>();
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Role r = new Role();
                r.setRoleId(rs.getInt(ID));
                r.setRoleName(rs.getString(NAME));
                list.add(r);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }
    @Override public void update(Role r) {
        String sql = "UPDATE " + T + " SET " + NAME + "=? WHERE " + ID + "=?";
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, r.getRoleName());
            ps.setInt(2, r.getRoleId());
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

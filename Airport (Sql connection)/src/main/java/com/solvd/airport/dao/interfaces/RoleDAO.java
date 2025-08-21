package com.solvd.airport.dao.interfaces;

import java.util.List;

import com.solvd.airport.models.Role;

public interface RoleDAO {
    void add(Role role);
    Role getById(int id);
    List<Role> getAll();
    void update(Role role);
    void delete(int id);
}

